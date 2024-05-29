import { IPayment } from "../model/payment.model";

export const initialState:IPayment = {
    id : 0,
    merchant_uid : '',
    name : '',
    amount : 0,
    regDate : '',
    modDate : '',
    payment_uid : '',
    order_uid: ''
}