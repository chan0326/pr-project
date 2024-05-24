import  instance  from "@/app/components/common/configs/axios-config"
import { IArticle } from "../model/article.model"

export const findAllArticlesAPI = async (page:number) =>{
    try {

        const response = await instance().get('/articles/list',{
            params: {page,limit: 10}
        })
        return response.data
        
    } catch (error) {
        console.log(error)
        return error
        
    }
}
export const findArticlesByIdAPI = async (id:number) =>{
    try {

        const response = await instance().get('/articles/detail',{
            params: {id,limit: 10}
        })
        return response.data
        
    } catch (error) {
        console.log(error)
        return error
        
    }
}
export const DeleteArticleByIdAPI = async (id:number) =>{
    try {

        const response = await instance().delete('/articles/delete',{
            params: {id,limit: 10}
        })
        return response.data
        
    } catch (error) {
        console.log(error)
        return error
        
    }
}
export const UpdateArticleByIdAPI = async (id:number) =>{
    try {

        const response = await instance().get('/articles/modifiy',{
            params: {id,limit: 10}
        })
        return response.data
        
    } catch (error) {
        console.log(error)
        return error
        
    }
}
export const findArticlesByBoardIdAPI = async (id:number) =>{
    try {

        const response = await instance().get('/articles/findByBoardId',{
            params: {id,limit: 10}
        })
        return response.data
        
    } catch (error) {
        console.log(error)
        return error
        
    }
}
export const saveArticleAPI = async (article:IArticle) => {
    try{
        const response = await instance().post('/articles/save',article)
        // java 에서 Messenger.message에 값을 담음
        return response.data
    } 
    catch(error){
        console.log(error)
        return error
    }
}