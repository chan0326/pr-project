export interface IPayment{
    id ? : number,
    merchant_uid? : string,
    name? : string,
    amount? : number,
    regDate? : string,
    modDate? : string,
    payment_uid? : string,
    order_uid: string,
    imp_uid?: string
}