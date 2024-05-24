import Typography from "@mui/material/Typography";
import { GridColDef } from "@mui/x-data-grid";
import { IArticle } from "../model/article.model";
import Link from "next/link";
import { PG } from "../../common/enums/PG";
interface CellType{
    row : IArticle

}
export default function Columns() : GridColDef[]{

    return [
        {
            flex:0.04,
            field: 'id',
            minWidth: 30,
            sortable:false,
            headerName: 'ID',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.5rem" }}>{row.id}</Typography>
        },
        {
            flex:0.04,
            field: 'title',
            minWidth: 30,
            sortable:false,
            headerName: '제목',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.5rem" }}>
                <Link href={`${PG.ARTICLE}/detail/${row.id}`}>{row.title}</Link></Typography>
        },
        {
            flex:0.04,
            field: 'content',
            minWidth: 30,
            sortable:false,
            headerName: '내용',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.5rem" }}>{row.content}</Typography>
        },
        {
            flex:0.04,
            field: 'regDate',
            minWidth: 30,
            sortable:false,
            headerName: 'regDate',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.5rem" }}>{row.regDate}</Typography>
        },
        {
            flex:0.04,
            field: 'modDate',
            minWidth: 30,
            sortable:false,
            headerName: 'modDate',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.5rem" }}>{row.modDate}</Typography>
        },
        {
            flex:0.04,
            field: 'boardType',
            minWidth: 30,
            sortable:false,
            headerName: 'boardType',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.5rem" }}>{row.boardId}</Typography>
        },
        {
                flex: 0.04,
                minWidth: 30,
                sortable: false,
                field: 'delete',
                headerName: '삭제',
                renderCell: ({row}:CellType) => <Link href={""}>  {<Typography textAlign="center" sx={{fontSize:"1.5rem"}}> 삭제 </Typography>}</Link>
                },
        
        
    ]
}