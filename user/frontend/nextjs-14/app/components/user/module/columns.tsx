import { Typography } from '@mui/material'
import {GridRowId,GridColDef} from '@mui/x-data-grid'
import { UserColumn } from '../model/UserColum'
import { IUser } from '../model/user.model'
import Link from 'next/link'
import { PG } from '../../common/enums/PG'
interface CellType{
    row : IUser

}
export default function Columns() : GridColDef[]{

    return [
        {
            flex:0.04,
            field: 'id',
            sortable:false,
            minWidth: 30,
            headerName: 'ID',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{row.id}</Typography>
            }
        ,
        {
            flex:0.04,
            field: 'username',
            sortable:false,
            minWidth: 30,
            headerName: '사용자ID',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>
                <Link href={`${PG.USER}/detail/${row.id}`}>{row.username}</Link></Typography>
        },
        {
            flex:0.04,
            field: 'password',
            sortable:false,
            minWidth: 30,
            headerName: '비밀번호',
            renderCell(){
                return<>**********</>
            }
        },
        {
            flex:0.04,
            field: 'name',
            sortable:false,
            minWidth: 30,
            headerName: '이름',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{row.name}</Typography>
        },
        {
            flex:0.04,
            field: 'phone',
            sortable:false,
            minWidth: 30,
            headerName: '전화번호',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{row.phone}</Typography>
        },
        {
            flex:0.04,
            field: 'job',
            minWidth: 30,
            headerName: '직업',
            renderCell:({row}:CellType) => <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{row.job}</Typography>
        }
    ]
}