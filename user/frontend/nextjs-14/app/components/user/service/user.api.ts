import  instance  from "@/app/components/common/configs/axios-config"
import { IUser } from "../model/user.model"

export const findAllUsersAPI = async (page:number) =>{
    try {

        const response = await instance().get('/users/list',{
            params: {page,limit: 10}
        })
        return response.data
        
    } catch (error) {
        console.log(error)
        return error
        
    }

}
export const findUserByIdAPI = async (id:number) =>{
    try {

        const response = await instance().get('/users/detail',{
            params: {id,limit: 10}
        })
        return response.data
        
    } catch (error) {
        console.log(error)
        return error
        
    }
}
export const loginAPI = async (user:IUser) => {
    try{
        const response = await instance().post('/auth/login',user)
        // java 에서 Messenger.message에 값을 담음
        return response.data
    } 
    catch(error){
        console.log(error)
        return error
    }
}
export const existsUsernameAPI = async (username:string) => {
    try{
        const response = await instance().get('/auth/existsByUsername',{
            params: {username}
        }
        )
        console.log(response.data)
        // java 에서 Messenger.message에 값을 담음
        return response.data
    } 
    catch(error){
        console.log(error)
        return error
    }
}
export const logoutAPI = async () => {
    try{
        const response = await instance().get('/users/logout'
      
        )
        console.log('logout 결과 :'+ response.data)
        return response.data
    } 
    catch(error){
        console.log(error)
        return error
    }
}