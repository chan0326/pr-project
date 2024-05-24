'use client'

import { DataGrid } from '@mui/x-data-grid';
import { useState, useEffect } from "react"
import { NextPage } from "next";
import { findAllArticles, saveArticle } from "@/app/components/article/service/article.service";
import { useSelector, useDispatch } from 'react-redux'
import { MyTypography } from '@/app/components/common/style/cell';
import { AttachFile, FmdGood, Numbers, ThumbUpAlt } from '@mui/icons-material';
import FmdGoodIcon from '@mui/icons-material/FmdGood';
import AttachFileIcon from '@mui/icons-material/AttachFile';
import ThumbUpAltIcon from '@mui/icons-material/ThumbUpAlt';
import { IArticle } from '@/app/components/article/model/article.model';
import { getauth } from '@/app/components/article/service/article.slice';
import { useRouter } from 'next/navigation';
import { Console } from 'console';
import {useForm} from 'react-hook-form';
import { tree } from 'next/dist/build/templates/app-page';
import { jwtDecode } from 'jwt-decode';
import { parseCookie } from 'next/dist/compiled/@edge-runtime/cookies';
import { parseCookies } from 'nookies';



export default function WriterArticlesPage ()  {

const {register , handleSubmit ,formState: {errors}} = useForm(); 


  const dispatch = useDispatch()
  const router = useRouter();

//   const handleonTitle = (e:any)=>{
//     setArticle({...article,
//       title: e.target.value})
    
// }
// const handleonContent = (e:any)=>{
//   setArticle({...article,
//     content: e.target.value})
// }
// const handleonId = (e:any)=>{
  
//   console.log("값은 ?"+ (e.target.value))
//   setArticle({...article,
//     boardId: parseInt(e.target.value)})
// }

    const handelCancel = ()=>{}
    // const handleSubmit = ()=>{
      
    //   dispatch(saveArticle(article))}
    //   useEffect(()=>{
    //     if(autharticle.message ==='SUCCESS'){
    //       router.push(`/pages/articles/search/${article.boardId}`)
    //     }else{
    //       console.log('save FAIL')
    //       console.log(autharticle.message)
    //     }
    //   },[autharticle])
      const Options = [
        { boardId: 1, title: "review", content: "리뷰게시판" },
        { boardId: 2, title: "qna", content: "Q&A" }, 
        { boardId: 3, title: "free", content: "자유게시판" }
      ]
    const onSubmit = (data:any) => {
      alert(JSON.stringify(data))
      dispatch(saveArticle(data))
      .then((res:any)=>{
        alert('게시글 작성 완료 : '+ JSON.stringify(res.payload))

        router.push(`/pages/articles/list/${data.boardId}`)
      })
      .catch((err:any)=>{});

    }  
     useEffect(()=>{
      console.log('토큰을 디코드한 내용 :')
      console.log(JSON.stringify(jwtDecode<any>(parseCookies().accessToken)))
        console.log('토큰을 디코드한 내용 id:')
        console.log(jwtDecode<any>(parseCookies().accessToken).userId)
    },[])


    return (<>
    
<form onSubmit={handleSubmit(onSubmit)} className="max-w-sm mx-auto">
  <label htmlFor="BOARDTYPE" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Select your BOARDTYPE</label>
  <select defaultValue={1}
  {...register('boardId',{required: true})}  id="boardId" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
  {
      Options.map((item,index)=>(
      <option key={item.boardId} title={item.title} value={item.boardId}> {item.content} </option>))}
  </select>









       <div className="editor mx-auto w-10/12 flex flex-col text-gray-800 border border-gray-300 p-4 shadow-lg max-w-2xl">
      {MyTypography('Article 작성', "1.5rem")}
      <input type="text" value={jwtDecode<any>(parseCookies().accessToken).boardId}/>
      <input {...register('title',{required: true, maxLength: 45})}
       className="title bg-gray-100 border border-gray-300 p-2 mb-4 outline-none" placeholder="Title" type="text" name="title" />
      <textarea {...register('content',{required: true, maxLength : 300 })} 
      className="description bg-gray-100 sec p-3 h-60 border border-gray-300 outline-none" placeholder="Describe everything about this post here" name="content" ></textarea>
      {/* <!-- icons --> */}
      <div className="icons flex text-gray-500 m-2">
        <svg className="mr-2 cursor-pointer hover:text-gray-700 border rounded-full p-1 h-7" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <ThumbUpAltIcon component={ThumbUpAlt}></ThumbUpAltIcon>
        </svg>
        <svg className="mr-2 cursor-pointer hover:text-gray-700 border rounded-full p-1 h-7" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <FmdGoodIcon component={FmdGood}></FmdGoodIcon>
        </svg>
        <svg className="mr-2 cursor-pointer hover:text-gray-700 border rounded-full p-1 h-7" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <AttachFileIcon component={AttachFile}></AttachFileIcon>
        </svg>
        <div className="count ml-auto text-gray-400 text-xs font-semibold">0/300</div>
      </div>
      {/* <!-- buttons --> */}
      <div className="buttons flex">
        <div className="btn  overflow-hidden relative w-30 bg-white text-blue-500 p-3 px-4 rounded-xl font-bold uppercase -- before:block before:absolute before:h-full before:w-1/2 before:rounded-full
        before:bg-pink-400 before:top-0 before:left-1/4 before:transition-transform before:opacity-0 before:hover:opacity-100 hover:text-200 hover:before:animate-ping transition-all duration-00"
          onClick={handelCancel}>Cancel</div>
        {/* <div className="btn  overflow-hidden relative w-30 bg-blue-500 text-white p-3 px-8 rounded-xl font-bold uppercase -- before:block before:absolute before:h-full before:w-1/2 before:rounded-full
        before:bg-pink-400 before:top-0 before:left-1/4 before:transition-transform before:opacity-0 before:hover:opacity-100 hover:text-200 hover:before:animate-ping transition-all duration-00"
          > Post </div> */}
          <input type="submit" value="SUBMIT" />
      </div>
    </div>
    </form>
    </>)
}

