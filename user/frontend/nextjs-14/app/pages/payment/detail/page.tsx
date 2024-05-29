'use client';
import React, { useEffect } from 'react';
import axios from 'axios';
import { SavePayment } from '@/app/components/payment/service/payment.service';
import { useDispatch } from 'react-redux';
const SERVER = 'http://localhost:8080'
const Payment = () => {
    const dispatch = useDispatch()
  useEffect(() => {
    const jquery = document.createElement("script");
    jquery.src = "http://code.jquery.com/jquery-1.12.4.min.js";
    const iamport = document.createElement("script");
    iamport.src = "http://cdn.iamport.kr/js/iamport.payment-1.1.7.js";
    document.head.appendChild(jquery);
    document.head.appendChild(iamport);
    return () => {
      document.head.removeChild(jquery);
      document.head.removeChild(iamport);
    };
  }, []);

  const requestPay = () => {

    

    const { IMP } = window;

    IMP.init('imp68704784');

    IMP.request_pay({
      pg: 'html5_inicis',
      pay_method: 'card',
      merchant_uid: new Date().getTime(),
      name: '테스트 상품',
      amount: 100,
      buyer_email: 'test@naver.com',
      buyer_name: '코드쿡',
      buyer_tel: '010-1234-5678',
      buyer_addr: '서울특별시',
      buyer_postcode: '123-456',
    }, function (rsp:any) {
        if (rsp.success) {
          alert('call back!!: ' + JSON.stringify(rsp));
          // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
          // axios로 HTTP 요청
          console.log(rsp)
          dispatch(SavePayment(rsp))
            
        } else {
          // 결제 실패 시 로직
          alert('결제 실패!' + rsp);
          window.location.href = '/fail-payment';
      }
    });
  };

  return (
    <div>
      <button onClick={requestPay}>결제하기</button>
    </div>
  );
};

export default Payment;