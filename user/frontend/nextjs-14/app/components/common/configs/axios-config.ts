import axios, { AxiosInstance } from "axios"
import { error } from "console"
import { parseCookies } from "nookies"
import { config } from "process"

// export default function AxiosConfig(){
//     return{
//         headers:{
    
//             "Cache-Control": "no-cache",
            
//             "Content-Type": "application/json",
            
//             Authorization: `Bearer blah ~` ,
            
//             "Access-Control-Allow-Origin": "*",
//     }
// }}

export default function instance(){
    const instance = axios.create({baseURL : process.env.NEXT_PUBLIC_API_URL})
    setInterceptor(instance)
    return instance
}


// instance.interceptors.request.use(
// (request) => {

//     const accesToken = parseCookies().accesToken;
//     console.log('axios 인터셉터에서 쿠키에서 토큰으로 추출함')
//     request.headers['Content-Type'] = "application/json"
//     request.headers['Authorization'] = `Bearer ${accesToken}`
//     return request
// },(error)=>{
//     console.log('axios interceptor instance 에서 발생된 에러'+error)
//     return Promise.reject(error)
// }
// )


// instance.interceptors.response.use(
//     (response)=>{
//         if(response.status === 404) {
//             console.log('axios interceptor instance 에서 발생된 에러fh 404로 넘어감')
//         }
//         return response
//     },(error)=>{
//         console.log('axios interceptor instance 에서 발생된 에러'+error)
//         return Promise.reject(error)
//     }
// )
export const setInterceptor = (inputInstance:AxiosInstance) => {
    inputInstance.interceptors.request.use(
    (request) => {
        console.log("inputInstance에서 받은 request : ")
        request.headers["Content-Type"] = "application/json"
        request.headers["Authorization"] = `Bearer ${parseCookies().accessToken}`
        return request
    }, (error) => {
        console.log("AXIOS INTERSEPTOR ERROR OCCURRED : " + error)
        return Promise.reject(error)
    })
    inputInstance.interceptors.response.use(
        (response) => {
            if(response.status === 404) console.log("AXIOS INTERSEPTOR CATCHES 404")
            return response
        }
    )
    return inputInstance
}



