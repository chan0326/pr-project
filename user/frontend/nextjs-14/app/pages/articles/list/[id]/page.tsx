'use client'

import MoveButton from "@/app/atoms/button/MoveButton";
import Columns from "@/app/components/article/module/columns";
import { findArticlesByBoardId } from "@/app/components/article/service/article.service";
import { getArticles } from "@/app/components/article/service/article.slice";
import { PG } from "@/app/components/common/enums/PG";
import { DataGrid } from "@mui/x-data-grid";
import { NextPage } from "next";
import Link from "next/link";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";


  const ArticleListPage : NextPage = ({params}: any) =>{
    const dispatch = useDispatch()
    const articles = useSelector(getArticles)

    useEffect(() => {
        dispatch(findArticlesByBoardId(params.id))
        console.log(params.id)
    }, [])

    return(
      <>

     <table  className="table-auto w-4/5 border-x-black" style={{margin: '50px auto'}}>
        <thead>
          <tr>
            <td>
              <MoveButton text={"글쓰기"} path={`${PG.ARTICLE}/save/${params.id}`} />
            </td>
          </tr>
        </thead>
        <tbody>
        <tr>
        <td 
        align="center" className="w-full  bg-gray-400 border-black border-4 p-8 h-20 text-[20px]" 
        >
       게시글 목록 2
        </td>
    </tr>
    <tr>
        <td align="center"  className="h-300">
     {articles && <DataGrid
        rows={articles}
        columns={Columns()}
        initialState={{
          pagination: {
            paginationModel: {
              pageSize: 5,
            },
          },
        }}
        pageSizeOptions={[5]}
        checkboxSelection
        disableRowSelectionOnClick
      />}
    </td>
    </tr>
       
        </tbody>
      </table>
    </>)
    }

      export default ArticleListPage
    





  