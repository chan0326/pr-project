'use client';
import { SavePayment } from '@/app/components/payment/service/payment.service';
import { Save } from '@mui/icons-material';
import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';


const RequestPay = () => {

  const dispatch = useDispatch()
  
  // 결제 요청 함수
  const requestPay = () => {
    if (!window.IMP) {
      console.error('IMP is not loaded');
      return;
    }
    window.IMP.request_pay({
      pg: "html5_inicis",
      pay_method: "card",
      merchant_uid: new Date().getTime(), 
      name: "스파게티면 500g",
      amount: 100,
      buyer_email: "gildong@gmail.com",
      buyer_name: "홍길동",
      buyer_tel: "010-4242-4242",
      buyer_addr: "서울특별시 강남구 신사동",
      buyer_postcode: "01181"
    }, (rsp:any) => {
      if (rsp.success) { 
        // 결제 성공 시 로직
        console.log('Payment succeeded');
        console.log(rsp.id,rsp.name,rsp.amount,rsp.success,rsp.merchant_uid);
        console.log(rsp)
        dispatch(SavePayment(rsp))
        // 추가로 실행할 로직을 여기에 작성
      } else {
        // 결제 실패 시 로직
        console.log('Payment failed', rsp.error_msg);
        // 추가로 실행할 로직을 여기에 작성
      }
    });
  };

  useEffect(() => {
    // 외부 스크립트 로드 함수
    const loadScript = (src:any, callback:any) => {
      const script = document.createElement('script');
      script.type = 'text/javascript';
      script.src = src;
      script.onload = callback;
      document.head.appendChild(script);
    };

    // 스크립트 로드 후 실행
    loadScript('https://code.jquery.com/jquery-1.12.4.min.js', () => {
      loadScript('https://cdn.iamport.kr/js/iamport.payment-1.2.0.js', () => {
        const IMP = window.IMP;
        // 가맹점 식별코드
        IMP.init("imp68704784");

        // DOMContentLoaded 이벤트 핸들러
        document.addEventListener('DOMContentLoaded', () => {
          const merchant_uid = "O" + new Date().getTime();
          const totalPriceElement = document.getElementById('totalPrice');
          const totalPrice = totalPriceElement ? totalPriceElement.innerText : '';

          fetch('payment/prepare', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              merchant_uid: merchant_uid, // 가맹점 주문번호
              amount: totalPrice // 결제 예정금액
            })
          });
        });
      });
    });

    // 컴포넌트가 언마운트될 때 스크립트를 제거하기 위한 정리 함수
    return () => {
      const scripts = document.querySelectorAll('script[src^="https://"]');
      scripts.forEach((script) => script.remove());
    };
  }, []);

  return (
    <div>
      <button onClick={requestPay}>Pay Now</button>
      <div id="totalPrice">100</div>
    </div>
  );
};

export default RequestPay;
