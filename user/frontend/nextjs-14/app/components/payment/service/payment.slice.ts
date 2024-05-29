import { createSlice } from "@reduxjs/toolkit";
import { IPayment } from "../model/payment.model";
import { SavePayment } from "./payment.service";
const paymentThunks = []
const status = {
    pending:'pending',
    fullfilled : 'fullfilled',
    rejected: 'rejected'
}
const handleFulfilled =  (state: any, {payload}: any) => {
    state.array = payload
}
interface paymentState{
    array? : Array<IPayment>,
    json? : IPayment,
    auth?: IAuth
}
interface IAuth{
    message?: string,
    token?: string
}
export const initialState:paymentState = {
    json : {} as IPayment,
    array : [],
    auth: {} as IAuth

}

export const paymentSlice = createSlice({
    name: "payment",
    initialState,
    reducers: {},
    extraReducers: builder => {
        const {pending, rejected} = status;

        // builder.addCase(findAllBoards.fulfilled, handleFulfilled)
        builder.addCase(SavePayment.fulfilled, (state: any, {payload}: any)=>{state.auth= payload})
        
    }
})

export const  getauth  = (state: any) => state.payment.auth;

export const {} = paymentSlice.actions
export default paymentSlice.reducer;