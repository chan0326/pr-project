package com.erichgamma.product.Payment.service;

import com.erichgamma.product.Payment.model.Payment;
import com.erichgamma.product.Payment.model.PaymentDto;
import com.erichgamma.product.common.compoent.MessengerVo;

public interface PaymentService {
    default Payment dtoToEntity(PaymentDto dto){
        return Payment.builder()
                .id(dto.getId())
                .status(dto.getStatus())
                .amount(dto.getAmount())
                .paymentUid(dto.getPaymentUid())
                .build();
    }


    MessengerVo save(PaymentDto dto);
}
