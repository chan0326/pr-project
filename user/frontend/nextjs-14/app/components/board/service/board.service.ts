import {createAsyncThunk} from "@reduxjs/toolkit"
import axios from "axios"
import { findABoardByIdAPI, findAllBoardsAPI } from "./board.api"

export const findAllBoards: any =createAsyncThunk('boards/findAllBoards',
async(page:number, {rejectWithValue})=>{
    console.log('findAllBoards page:'+ page)
    
    const data:any = await  findAllBoardsAPI(1);

        const {message, result}:any = data
        // console.log('----- API 를 사용한 경우 -----')
        // console.log('message : '+ message)
        // console.log(JSON.stringify(result))
        return data
}
)
export const findBoardById: any = createAsyncThunk('boards/findBoardById',
async(id:number, {})=>{
    
    const data:any = await  findABoardByIdAPI(id);
        const {message, result}:any = data
        return data
}

)
