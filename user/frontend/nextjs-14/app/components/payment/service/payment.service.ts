import {createAsyncThunk} from "@reduxjs/toolkit"
import { SavePaymentAPI } from "./payment.api";
import { IPayment } from "../model/payment.model";
export const SavePayment: any =createAsyncThunk('/payment/SavePayment',
async(payment:IPayment, {rejectWithValue})=>  await SavePaymentAPI(payment)
)