package site.toeicdoit.api.payment.service;




import site.toeicdoit.api.common.component.enums.PaymentStatus;
import site.toeicdoit.api.order.model.OrderModel;
import site.toeicdoit.api.order.repository.OrderRepository;
import site.toeicdoit.api.user.repository.UserRepository;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.request.PrepareData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.toeicdoit.api.payment.model.PaymentCallbackRequest;
import site.toeicdoit.api.payment.model.RequestPayDto;
import site.toeicdoit.api.payment.repository.PaymentRepository;

import java.io.IOException;
import java.math.BigDecimal;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final IamportClient iamportClient;
    private final UserRepository userRepository;


    @Override
    public RequestPayDto findRequestDto(String orderUid) {
        OrderModel orderModel = orderRepository.findOrderAndPaymentAndMember(orderUid)
                .orElseThrow(() -> new IllegalArgumentException("주문이 없습니다."));

        return RequestPayDto.builder()
                .buyerName(orderModel.getUserModel().getUsername())
                .buyerEmail(orderModel.getUserModel().getEmail())
                .buyerAddress(orderModel.getUserModel().getAddressId())
                .paymentPrice(orderModel.getPaymentModel().getAmount())
                .itemName(orderModel.getItemName())
                .orderUid(orderModel.getOrderUid())
                .build();
    }

    @Override
    public IamportResponse<Payment> paymentByCallback(PaymentCallbackRequest request) {
        try {
            // 결제 단건 조회(아임포트)
            log.info("call back 에서 들어온 request ={}", request.toString());
            IamportResponse<Payment> iamportResponse = iamportClient.paymentByImpUid(request.getPaymentUid());
            log.info("결제 응답 call back, service ={}", iamportResponse.getResponse().toString());
            // 주문내역 조회
            OrderModel orderModel = orderRepository.findOrderAndPayment(request.getOrderUid())
                    .orElseThrow(() -> new IllegalArgumentException("주문 내역이 없습니다."));

            // 결제 완료가 아니면
            if(!iamportResponse.getResponse().getStatus().equals("paid")) {
                // 주문, 결제 삭제
                orderRepository.delete(orderModel);
                paymentRepository.delete(orderModel.getPaymentModel());

                throw new RuntimeException("결제 미완료");
            }

            // DB에 저장된 결제 금액

            Long price = orderModel.getPaymentModel().getAmount();
            orderRepository.save(OrderModel.builder().itemName(orderModel.getItemName()).orderUid(orderModel.getOrderUid())
                    .price(orderModel.getPrice()).build());
            // 실 결제 금액
            int iamportPrice = iamportResponse.getResponse().getAmount().intValue();

            // 결제 금액 검증
            if(iamportPrice != price) {
                // 주문, 결제 삭제
                orderRepository.delete(orderModel);
                paymentRepository.delete(orderModel.getPaymentModel());

                // 결제금액 위변조로 의심되는 결제금액을 취소(아임포트)
                iamportClient.cancelPaymentByImpUid(new CancelData(iamportResponse.getResponse().getImpUid(), true, new BigDecimal(iamportPrice)));

                throw new RuntimeException("결제금액 위변조 의심");
            }

            // 결제 상태 변경
            orderModel.getPaymentModel().changePaymentBySuccess(PaymentStatus.OK, iamportResponse.getResponse().getImpUid());

            return iamportResponse;

        } catch (IamportResponseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void postPrepare(Payment request) throws IamportResponseException, IOException {
        PrepareData prepareData = new PrepareData(request.getMerchantUid(), request.getAmount());
        iamportClient.postPrepare(prepareData);  // 사전 등록 API



    }



}



