'use client'

import { DataGrid } from '@mui/x-data-grid';
import { useState, useEffect } from "react"
import { NextPage } from "next";
import { useSelector, useDispatch } from 'react-redux'
import Columns from "@/app/components/board/module/columns";
import { Button, Typography } from '@mui/material';
import { findUserById } from '@/app/components/user/service/user.service';
import { getfindUserById } from '@/app/components/user/service/user.slice';
import { retry } from '@reduxjs/toolkit/query';
import { IUser } from '@/app/components/user/model/user.model';
import axios from 'axios';
import AxiosConfig from '@/app/components/common/configs/axios-config';
import { PG } from '@/app/components/common/enums/PG';
import { useRouter } from 'next/navigation';

export default function UserDetailPage ({params}:any){
    const dispatch = useDispatch()
    const user:IUser = useSelector(getfindUserById)




    useEffect(()=>(dispatch(findUserById(params.id))),[])


    return(<>
    
   <span>ID :</span>   <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{params.id}</Typography>
    <span>아이디 :</span>   <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{user.username}</Typography>
    <span>이름 :</span>   <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{user.name}</Typography>
    <span>비밀번호 :</span>   <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{user.password}</Typography>
    <span>전화번호 :</span>   <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{user.phone}</Typography>
    <span>직업 :</span>   <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{user.job}</Typography>
    
    <button> 수정 </button>
    <button> 탈퇴 </button>
    </>)
}