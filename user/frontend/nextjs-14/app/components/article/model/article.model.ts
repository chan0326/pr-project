import { ReactNode } from "react"

export interface IArticle{
    id?: number,
    title?: string,
    content?: string,
    writer?: string,
    boardId?: number,
    regDate?: string,
    modDate?: string,
    count?: number
    
}