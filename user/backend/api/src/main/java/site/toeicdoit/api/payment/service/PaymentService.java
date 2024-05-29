package site.toeicdoit.api.payment.service;

import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import site.toeicdoit.api.payment.model.PaymentCallbackRequest;
import site.toeicdoit.api.payment.model.RequestPayDto;

import java.io.IOException;

public interface PaymentService {


    RequestPayDto findRequestDto(String orderUid);
    // 결제(콜백)
    IamportResponse<Payment> paymentByCallback(PaymentCallbackRequest request);

    public <S> void postPrepare(Payment request) throws IamportResponseException, IOException;



}
