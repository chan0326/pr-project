// components/service/PaymentService.tsx
import React, { useEffect } from 'react';

const PaymentService: React.FC = () => {
  useEffect(() => {
    const script = document.createElement('script');
    script.src = 'https://js.tosspayments.com/v1/payment';
    script.async = true;
    script.onload = () => {
      // 초기화 후 사용할 수 있습니다.
    };
    document.body.appendChild(script);

    return () => {
      document.body.removeChild(script);
    };
  }, []);

  const handlePayment = () => {
    const tossPayments = (window as any).TossPayments('test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq');
    const amount = document.getElementById('amount')?.innerText;
    const orderId = document.getElementById('orderId')?.innerText;
    const orderName = document.getElementById('orderName')?.innerText;
    const customerName = document.getElementById('customerName')?.innerText;

    if (amount && orderId && orderName && customerName) {
      tossPayments.requestPayment('카드', {
        amount: amount,
        orderId: orderId,
        orderName: orderName,
        customerName: customerName,
        successUrl: 'http://localhost:3000/success',
        failUrl: 'http://localhost:3000/fail',
      }).catch((error: any) => {
        if (error.code === 'USER_CANCEL') {
          // 결제 고객이 결제창을 닫았을 때 에러 처리
        } else if (error.code === 'INVALID_CARD_COMPANY') {
          // 유효하지 않은 카드 코드에 대한 에러 처리
        }
      });
    }
  };

  return (
    <div className="single-head t-center">
      <div className="contant-inner-title">
        <h2>참여 정보</h2>
        <span>
          <span>OrderNumber:</span>
          <span id="orderId">POST-C-ORDER-2023-02-12-07661e23-e4df-47b6-be86-3867a13fa7bc</span>
        </span><br />
        <span>
          <span>참여게시물 타이틀:</span>
          <span id="orderName">샐러드 재료 파프리카 모집합니다.</span>
        </span><br />
        <span>
          <span>참여자:</span>
          <span id="customerName">이기업</span>
        </span>
      </div>

      <div className="contant-inner-title">
        <h2>현재 잔여포인트 확인</h2>
        <span>현재 잔여포인트:</span>
        <span id="total-amount">0</span><br />

        <span>부족한 포인트:</span>
        <span id="amount">2800000</span>
      </div>

      <div className="d-grid gap-2 m-t-20">
        <p>앗! 포인트 충전이 필요합니다!</p>
        <button id="payment_card_button" className="btn btn-warning size-h-65 payment-btn" type="button" onClick={handlePayment}>
          포인트 충전 바로가기
        </button>
      </div>
    </div>
  );
};

export default PaymentService;
