import Typography from "@mui/material/Typography";
import { GridColDef } from "@mui/x-data-grid";
import { IBoard } from "../model/board.model";
import Link from "next/link";
import { PG } from "../../common/enums/PG";
interface CellType{
    row : IBoard

}
export default function Columns() : GridColDef[]{

    return [
        {
            flex:0.04,
            field: 'id',
            minWidth: 30,
            sortable:false,
            headerName: 'ID',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{row.id}</Typography>
        },
        {
            flex:0.04,
            field: 'title',
            minWidth: 30,
            sortable:false,
            headerName: 'title',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>
                <Link href={`${PG.BOARD}/detail/${row.id}`}>{row.title}</Link></Typography>
        },
        {
            flex:0.04,
            field: 'description',
            minWidth: 30,
            sortable:false,
            headerName: 'description',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{row.description}</Typography>
        },
        {
            flex:0.04,
            field: 'modDate',
            minWidth: 30,
            sortable:false,
            headerName: 'modDate',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{row.modDate}</Typography>
        },
        {
            flex:0.04,
            field: 'regDate',
            minWidth: 30,
            sortable:false,
            headerName: 'regDate',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{row.regDate}</Typography>
        },
       
    ]
}