import React, { useCallback, useEffect, useState } from "react";
import { addReply, deleteOneReply, updateOneReply } from "../lib/api";
import "./css/reply.css";
import { MdDeleteForever } from 'react-icons/md';
import { AiTwotoneEdit } from 'react-icons/ai';
import { Button, TextField } from "@mui/material";

const Reply = ({ commentList, postId }) => {
    const username = sessionStorage.getItem("name");
    const [replyList, setReplyList] = useState(commentList);
    const [reply, setReply] = useState("");

    const [oneReply, setOneReply] = useState("");
    const [index, setIndex] = useState(0);
    
    const [flag, setFlag] = useState(false);
    useEffect(() => {
        setReplyList(commentList);
    }, [commentList]);

    const changeReplyText = useCallback(e => {
        setReply(e.target.value)
    }, []);

    const changeOneReply = useCallback(e => {
        setOneReply(e.target.value);
    }, []);

    const click = useCallback(async () => {
        if (reply === "") {
            alert("please fill in the reply");
            return;
        }
        const response = await addReply({ name: username, postId, reply });
        setReplyList(replyList => [...replyList, {
             name: username, commentId: response.data, reply
        }])
        setReply("");
    }, [postId, reply, username]);

    const updateOne = useCallback(async (e, data) => {
        if (flag === false) {
            setFlag(true);
        } else if (data.commentId === index) {
            if (oneReply === "" || oneReply === data.reply) {
                setFlag(false);
                return;
            }
            await updateOneReply({ ...data, commentId: index, reply: oneReply });
            setReplyList(replyList => replyList.map(value => {
                if (value.commentId === index) value.reply = oneReply;
                return value;
            }))
            setFlag(false);
        }
        setIndex(data.commentId);
        setOneReply(data.reply);
    }, [flag, oneReply, index]);

    const deleteOne = useCallback(async (data) => {
        await deleteOneReply(data);
        setReplyList(replyList => replyList.filter(value => value.commentId !== data.commentId));
    }, []);

    return (
        <div className="reply">
            <TextField onChange={changeReplyText} value={reply} className="text-input" label="reply" variant="standard" />
            <Button className="add-reply" size="small" onClick={click} variant="outlined">WRITE</Button>
            <div className="reply-list">
                {replyList.map(value =>
                    <div key={value.commentId}>
                        <div className="reply">
                            {
                                value.commentId === index && flag ?
                                    <textarea value={oneReply} onChange={changeOneReply} /> :
                                    <div>{value.reply}</div>
                            }
                        </div>
                        <div className="userId">
                            {value.name === "--" ? "NONE" : value.name}
                        </div>
                        {
                            value.name === username ?
                                <div>
                                    <div className="update-icon"
                                        style={{ color: value.commentId === index && flag ? "skyblue" : "#2f31bb" }}
                                        onClick={e => updateOne(e, { postId, commentId: value.commentId, reply: value.reply })}>
                                        <AiTwotoneEdit />
                                    </div>
                                    <MdDeleteForever className="remove-icon" onClick={() => deleteOne({ postId, commentId: value.commentId })} />
                                </div> : null
                        }
                        <hr className="reply-boundary" />
                    </div>
                )}
            </div>
        </div>
    )
}

export default React.memo(Reply);
