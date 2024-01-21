import React from "react";
import { Link } from "react-router-dom";
import "./css/List.css";
import Paper from '@mui/material/Paper';
import TableContainer from '@mui/material/TableContainer';
import Table from '@mui/material/Table';
import TableHead from '@mui/material/TableHead';
import TableBody from '@mui/material/TableBody';
import TableRow from '@mui/material/TableRow';
import TableCell from '@mui/material/TableCell';
import Button from '@mui/material/Button';
const List = ({ lists }) => {

    return (
        <TableContainer className="table-container" sx={{ width: "100vw", bgcolor: "#f9f9f9" }} component={Paper} >
            <Table>
                <TableHead className="head">
                    <TableRow >
                        <TableCell width="50">ID</TableCell>
                        <TableCell width="20">VIEW</TableCell>
                        <TableCell>TITLE</TableCell>
                        <TableCell>TEXT</TableCell>
                        <TableCell></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {lists.map((value, index) =>
                        <TableRow key={value.postId}>
                            <TableCell>{value.name}</TableCell>
                            <TableCell className="td">{value.view}</TableCell>
                            <TableCell className="td">{value.title}</TableCell>
                            <TableCell className="td">{value.text}</TableCell>
                            <TableCell width="82">
                                <Link to={{
                                    pathname: `/view/${index + 1}`,
                                    state: {
                                        postId: value.postId,
                                        name: value.name,
                                        title: value.title
                                    }
                                }}>
                                    <Button
                                        style={{
                                            backgroundColor: "#748DA6"
                                        }}
                                        className="mui-btn"
                                        variant="contained">
                                        DETAIL
                                    </Button>
                                </Link>
                            </TableCell>
                        </TableRow>
                    )}
                </TableBody>
            </Table>
        </TableContainer >
    )
}

export default React.memo(List);
