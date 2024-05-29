import { IPayment } from "../model/payment.model";
import Link from "next/link";
import { PG } from "../../common/enums/PG";
import Typography from "@mui/material/Typography";
import { GridColDef } from "@mui/x-data-grid";
interface CellType{
    row : IPayment
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
            field: 'merchant_uid',
            minWidth: 30,
            sortable:false,
            headerName: 'merchant_uid',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>
                <Link href={`${PG.PAY}/detail/${row.id}`}>{row.merchant_uid}</Link></Typography>
        },
        {
            flex:0.04,
            field: 'name',
            minWidth: 30,
            sortable:false,
            headerName: 'name',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{row.name}</Typography>
        },
        {
            flex:0.04,
            field: 'amount',
            minWidth: 30,
            sortable:false,
            headerName: 'amount',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{row.amount}</Typography>
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