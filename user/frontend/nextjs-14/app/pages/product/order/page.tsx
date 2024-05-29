import React, { useState } from 'react';
import Link from 'next/link';
import { useSelector, useDispatch } from 'react-redux';


const Order = () => {
  const dispatch = useDispatch();
  const [orderUid] = useState(new Date().getTime());

  const handleOrder = () => {
    
  };

  return (
    <div>
      <h1>주문 페이지</h1>
      <button onClick={handleOrder}>주문하기</button>
      <div style={{ marginTop: '20px' }}>
        <Link href={`/payment?orderUid=${orderUid}`}>
          결제 페이지로 이동
        </Link>
      </div>
    </div>
  );
};

export default Order;
