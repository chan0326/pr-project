package com.erichgamma.product.Payment.service;
import com.erichgamma.product.Payment.model.Payment;
import com.erichgamma.product.Payment.model.PaymentDto;
import com.erichgamma.product.Payment.repository.PaymentRepository;
import com.erichgamma.product.common.compoent.MessengerVo;
import com.siot.IamportRestClient.IamportClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;
    private final IamportClient iamportClient;

    @Override
    public MessengerVo save(PaymentDto dto) {
        Payment ent = paymentRepository.save(dtoToEntity(dto));
        System.out.println(" ============ PaymentServiceImpl save instanceof =========== ");
        System.out.println((ent instanceof Payment) ? "SUCCESS" : "FAILURE");
        return MessengerVo.builder()
                .message((ent instanceof Payment) ? "SUCCESS" : "FAILURE")
                .id(ent.getId())
                .build();
    }
}
