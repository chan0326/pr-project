'use client'

import { DataGrid } from '@mui/x-data-grid';
import { useState, useEffect } from "react"
import { NextPage } from "next";
import { useSelector, useDispatch } from 'react-redux'
import Columns from "@/app/components/board/module/columns";
import { getAllBoards } from "@/app/components/board/service/board.slice";
import { findAllBoards } from "@/app/components/board/service/board.service";
import { Button, Typography } from '@mui/material';
import { DeleteArticleById, UpdateArticleById, findArticleById } from '@/app/components/article/service/article.service';
import { getfindArticleById} from '@/app/components/article/service/article.slice';
import axios from 'axios';
import AxiosConfig from '@/app/components/common/configs/axios-config';
import { useRouter } from 'next/navigation';
const SERVER = 'http://localhost:8080'
export default function articleDetailPage ({params}:any){
    const router = useRouter();
    const [title,Settitle] = useState('')
    const [content,Setcontent] = useState('')
    const handleonTitle = (e:any)=>{
        Settitle(e.target.value)
    }
    const handleonContent = (e:any)=>{
        Setcontent(e.target.value)
    }
    const dispatch = useDispatch()
    const article = useSelector(getfindArticleById)

    useEffect(()=>(dispatch(findArticleById(params.id))),[])

    const DeleteonClick = ((e:any)=>(dispatch(DeleteArticleById(params.id))))
    const UpdateonClick = (()=>(dispatch(UpdateArticleById({title,content}))))
    
    
    return(<>
    <span>ID :</span>   <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{params.id}</Typography>
    <span>title :</span>   <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{article.title}</Typography>
    <span>content :</span>   <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{article.content}</Typography>
    <span>writer :</span>   <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{article.writer}</Typography>
    <span>regDate :</span>   <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{article.regDate}</Typography>
    <span>modDate :</span>   <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{article.modDate}</Typography>


    <h3> Update</h3>
    <label htmlFor="title">제목<b></b></label>
    <input type="text"onChange={handleonTitle} placeholder="Enter title" name="title" required /><br />
    <label htmlFor="title">내용<b></b></label>
    <input type="text"onChange={handleonContent} placeholder="Enter title" name="title" required /><br />
    <Button  onClick={UpdateonClick}>수정</Button ><br />
    <Button  onClick={DeleteonClick}>삭제</Button ><br />
    </>)
}

