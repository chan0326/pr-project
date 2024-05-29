package site.toeicdoit.api.order.service;

import site.toeicdoit.api.payment.model.PaymentModel;
import site.toeicdoit.api.payment.repository.PaymentRepository;
import site.toeicdoit.api.common.component.enums.PaymentStatus;
import site.toeicdoit.api.order.model.OrderModel;
import site.toeicdoit.api.order.repository.OrderRepository;
import site.toeicdoit.api.user.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    @Override
    public OrderModel autoOrder(UserModel userModel) {
        PaymentModel paymentModel = PaymentModel.builder()
                .amount(1000L)
                .status(PaymentStatus.READY)
                .build();

        paymentRepository.save(paymentModel);

        // 주문 생성
        OrderModel orderModel = OrderModel.builder()
                .user(userModel)
                .price(1000L)
                .itemName("1달러샵 상품")
                .orderUid(UUID.randomUUID().toString())
                .payment(paymentModel)
                .build();

        return orderRepository.save(orderModel);
    }
}
