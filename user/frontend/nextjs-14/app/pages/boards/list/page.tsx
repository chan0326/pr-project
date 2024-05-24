'use client'
import CardButton from "@/app/atoms/button/CardButton";
import { IBoard } from "@/app/components/board/model/board.model";
import { findAllBoards } from "@/app/components/board/service/board.service";
import { getAllBoards } from "@/app/components/board/service/board.slice";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

export default function BoardCards() {
    const dispatch = useDispatch()
    const BoardList = useSelector(getAllBoards);

    useEffect(() => {
        dispatch(findAllBoards(1))
    }, [])
    return (<>

        <h1> 게시판 목록 들어옴</h1>

        {BoardList.map((board: IBoard) => (<CardButton id={board.id}  title={board.title}  description={board.description}/> ))
    }

    </>)
}