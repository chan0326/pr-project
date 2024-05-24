import { createSlice } from "@reduxjs/toolkit";
import { findAllArticles, findArticleById, findArticlesByBoardId, saveArticle } from "./article.service";
import { Pending } from "@mui/icons-material";
import build from "next/dist/build";
import { IArticle } from "../model/article.model";

const articleThunks = [findAllArticles,findArticleById]
const status = {
    pending:'pending',
    fullfilled : 'fullfilled',
    rejected: 'rejected'
}

const handleFulfilled =  (state: any, {payload}: any) => {
    state.array = payload
}
interface ArticleState  {
    array? : Array<IArticle>,
    json?:IArticle,
    auth?: IAuth
}
interface IAuth{
    message?: string,
    token?: string
}
export const initialState:ArticleState = {
    json: {} as IArticle,
    array : [],
    auth: {} as IAuth
}



export const articleSlice = createSlice({
    name: "article",
    initialState,
    reducers: {},
    extraReducers: builder => {
        const {pending, rejected} = status;

        builder.addCase(findAllArticles.fulfilled, handleFulfilled)
        builder.addCase(findArticleById.fulfilled, (state: any, {payload}: any)=>{state.json = payload})
        builder.addCase(findArticlesByBoardId.fulfilled, handleFulfilled)
        builder.addCase(saveArticle.fulfilled,(state:any,{payload}:any)=>{state.auth =payload})
    }
})

export const getAllArticles  = (state: any) => {
    return state.article.array;
}
export const getfindArticleById  = (state: any) =>  state.article.json;
export const getArticles = (state : any) => state.article.array;
export const  getauth  = (state: any) => state.article.auth;

export const {} = articleSlice.actions
export default articleSlice.reducer;