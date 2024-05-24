import { createAsyncThunk } from "@reduxjs/toolkit";
import { existsUsernameAPI, findAllUsersAPI, findUserByIdAPI, loginAPI, logoutAPI } from "./user.api";
import { IUser } from "../model/user.model";

export const findAllUsers: any = createAsyncThunk('users/findAllUsers',
async(page:number, {rejectWithValue})=>{
    console.log('fetchAllUsers page:'+ page)

    const data:any = await  findAllUsersAPI(page);

    const {message, result}:any = data
        console.log('----- API 를 사용한 경우 -----')
        console.log('message : '+ message)
        console.log(JSON.stringify(result))
        return data

}
)
export const findUserById: any = createAsyncThunk('users/findUserById',
async(id:number, {rejectWithValue})=>{

    const data:any = await  findUserByIdAPI(id);
    const {message, result}:any = data
        return data
}
)
export const findlogin: any = createAsyncThunk('users/findlogin',
    async(user:IUser)=> await loginAPI(user)
)
export const existsUsername: any = createAsyncThunk('users/existsUsername',
    async(username:string)=> await existsUsernameAPI(username)
)
export const logout: any = createAsyncThunk('users/logout',
    async()=> await logoutAPI()
)

