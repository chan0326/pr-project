import { createSlice } from "@reduxjs/toolkit";
import { Pending } from "@mui/icons-material";
import build from "next/dist/build";
import { findAllBoards, findBoardById } from "./board.service";
import { IBoard } from "../model/board.model";

const boardThunks = [findAllBoards]
const status = {
    pending:'pending',
    fullfilled : 'fullfilled',
    rejected: 'rejected'
}

const handleFulfilled =  (state: any, {payload}: any) => {
    state.array = payload
}


interface BoardState{
    array? : Array<IBoard>,
    json? : IBoard
}

export const initialState:BoardState = {
    json : {} as IBoard,
    array : []

}


export const boardSlice = createSlice({
    name: "board",
    initialState,
    reducers: {},
    extraReducers: builder => {
        const {pending, rejected} = status;

        builder.addCase(findAllBoards.fulfilled, handleFulfilled)
        builder.addCase(findBoardById.fulfilled, (state: any, {payload}: any)=>{state.json= payload})
        
    }
})

export const getAllBoards  = (state: any) => state.board.array
export const getSingleBoard  = (state: any) => state.board.json

export const {} = boardSlice.actions
export default boardSlice.reducer;