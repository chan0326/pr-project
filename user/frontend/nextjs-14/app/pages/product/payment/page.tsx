'use client';
import React, { useEffect } from 'react';
import router, { useRouter } from 'next/router';
import { useDispatch, useSelector } from 'react-redux';
import axios from 'axios';
import { SavePayment } from '@/app/components/payment/service/payment.service';



const SERVER = 'http://localhost:8080';

const Payment = () => {
  const dispatch = useDispatch();
  const  orderUid  = new Date().getTime();
  useEffect(() => {

    const loadScript = async (src: string) => {
      return new Promise<void>((resolve, reject) => {
        const script = document.createElement("script");
        script.src = src;
        script.onload = () => resolve();
        script.onerror = () => reject(new Error(`Script load error: ${src}`));
        document.head.appendChild(script);
      });
    };

    loadScript("https://code.jquery.com/jquery-1.12.4.min.js")
      .then(() => loadScript("https://cdn.iamport.kr/v1/iamport.js"))
      .catch(error => console.error('Script loading error:', error));
  }, []);

  const requestPay = () => {
    const { IMP } = window as any;
    IMP.init("imp68704784");

    const paymentData = {
      pg: 'html5_inicis',
      pay_method: 'card',
      merchant_uid: orderUid,
      name: '상품명',
      amount: 100,
      buyer_email: 'buyer@example.com',
      buyer_name: '구매자',
      buyer_tel: '010-1234-5678',
      buyer_addr: '주소',
      buyer_postcode: '123-456'
    };

    IMP.request_pay(paymentData, (rsp: any) => {
      if (rsp.success) {
        axios.post(`${SERVER}/api/payment/verifyIamport/${impUid}`, {
          payment_uid: rsp.imp_uid,
          order_uid: rsp.merchant_uid
        })
        .then(() => {
          alert('결제가 완료되었습니다.');
          dispatch(SavePayment(rsp));
          
        })
        .catch(error => {
          console.error(error);
          alert('서버와 통신 중 오류가 발생했습니다.');
          
        });
      } else {
        alert(`결제에 실패하였습니다. 에러 내용: ${rsp.error_msg}`);
        
      }
    });
  };

  return (
    <div>
      <h1>결제 페이지</h1>
      <button onClick={requestPay}>결제하기</button>
    </div>
  );
};

export default Payment;
