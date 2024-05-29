'use client';

import React, { useEffect } from 'react';
import axios from 'axios';
import { useDispatch } from 'react-redux';
import { SavePayment } from '@/app/components/payment/service/payment.service';

const Payment = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    const jquery = document.createElement('script');
    jquery.src = 'http://code.jquery.com/jquery-1.12.4.min.js';
    const iamport = document.createElement('script');
    iamport.src = 'http://cdn.iamport.kr/js/iamport.payment-1.1.7.js';
    document.head.appendChild(jquery);
    document.head.appendChild(iamport);
    return () => {
      document.head.removeChild(jquery);
      document.head.removeChild(iamport);
    };
  }, []);

  const requestPay = async () => {
    window.IMP.init('imp68704784');

    window.IMP.request_pay(
      {
        pg: 'html5_inicis',
        pay_method: 'card',
        merchant_uid: new Date().getTime().toString(), // 고유 주문 번호
        name: '테스트 상품',
        amount: 100,
        buyer_email: 'test@naver.com',
        buyer_name: '코드쿡',
        buyer_tel: '010-1234-5678',
        buyer_addr: '서울특별시',
        buyer_postcode: '123-456',
      },
      async (rsp: any) => {
        try {
          if (rsp.success) {
            console.log(rsp.imp_uid);
            const { data } = await axios.post(`http://localhost:8080/api/payment/verifyIamport/${rsp.imp_uid}`);
            if (rsp.paid_amount === data.response.amount) {
              const paymentData = {
                amount: data.response.amount,
                status: 'OK', // Assuming success means status 'OK'
                paymentUid: rsp.imp_uid
              };
              dispatch(SavePayment(paymentData))
              console.log('data.response.amount:', data.response);
              alert('결제 성공');
            } else {
              alert('결제 실패');
            }
          } else {
            alert('결제 실패');
          }
        } catch (error) {
          console.error('Error while verifying payment:', error);
          alert('결제 실패');
        }
      }
    );
  };

  return (
    <div>
      <button onClick={requestPay}>결제하기</button>
    </div>
  );
};

export default Payment;

