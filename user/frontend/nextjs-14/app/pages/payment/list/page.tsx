'use client';
import React, { useEffect } from 'react';
import axios from 'axios';
import { useDispatch } from 'react-redux';
import { SavePayment } from '@/app/components/payment/service/payment.service';

const SERVER = 'http://localhost:8080';

const PaymentKakao = () => {
  const dispatch = useDispatch();

  useEffect(() => {
    const loadScript = async (src:any) => {
      return new Promise((resolve, reject) => {
        const script = document.createElement("script");
        script.src = src;
        script.onload = resolve;
        script.onerror = reject;
        document.head.appendChild(script);
      });
    };

    loadScript("https://code.jquery.com/jquery-1.12.4.min.js")
      .then(() => loadScript("https://cdn.iamport.kr/v1/iamport.js"))
      .catch(error => console.error('Script loading error:', error));

    return () => {
      document.head.querySelectorAll("script[src='https://code.jquery.com/jquery-1.12.4.min.js'], script[src='https://cdn.iamport.kr/v1/iamport.js']")
        .forEach(script => script.remove());
    };
  }, []);

  const handleKakaoPayment = () => {
    const { IMP } = window;
    IMP.init('imp68704784');

    const money = 100;
    const id = new Date().getTime();

    IMP.request_pay({
      pg: 'html5_inicis',
      merchant_uid: id,
      name: '주문명 : 주문명 설정',
      amount: money,
      buyer_email: 'iamport@siot.do',
      buyer_name: '구매자이름',
      buyer_tel: '010-1234-5678',
      buyer_addr: '인천광역시 부평구',
      buyer_postcode: '123-456'
    }, function (rsp: any) {
      console.log(rsp, '1차-----');
      dispatch(SavePayment(rsp));
      if (rsp.success) {
        let msg = '결제가 완료되었습니다.';
        msg += ' 고유ID : ' + rsp.imp_uid;
        msg += ' 상점 거래ID : ' + rsp.merchant_uid;
        msg += ' 결제 금액 : ' + rsp.paid_amount;
        msg += ' 카드 승인번호 : ' + rsp.apply_num;
        console.log(msg, '2차-----');
        dispatch(SavePayment(msg));

        axios.post(`${SERVER}/api/point/charge`, { amount: money, id: id })
          .then(() => {
            alert(msg);
            console.log(msg);
            console.log('결제 금액 전송 완료');
            dispatch(SavePayment(msg));
          })
          .catch(error => {
            console.error(error);
            alert('충전 금액 전송 중 오류가 발생했습니다.');
          });
      } else {
        let msg = '결제에 실패하였습니다.';
        msg += ' 에러내용 : ' + rsp.error_msg;
        alert(msg);
      }
    });
  };

  return (
    <div>
      <button id="charge_kakao" onClick={handleKakaoPayment}>결제하기</button>
    </div>
  );
};

export default PaymentKakao;
