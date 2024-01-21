import { Button } from "@mui/material";
import React, { useCallback, useEffect, useState } from "react";
import { MdArrowBack, MdDeleteOutline, MdEdit } from "react-icons/md";
import { deleteData,  updateData, ViewPost } from "../lib/api";
import "./css/view.css"
import Recommend from "./recommend";
import Reply from "./reply";

const deleted = async (id, history) => {
    await deleteData(id);
    history.goBack();
}

const View = ({ location, match, history }) => {
    const { postId, name, title } = location.state;
    const { number: n } = match.params;
    const username = sessionStorage.getItem("name");

    const [viewText, setViewText] = useState("");
    const [info, setInfo] = useState({
        view: 0, good: 0, bad: 0
    });
    const [commentList, setCommentList] = useState([{
        name: "",
        commentId: 0,
        reply: ""
    }]);

    const [flag, setFlag] = useState(true);

    const textChange = e => setViewText(e.target.value);
    useEffect(() => {
        const viewAndText = async () => {
            ViewPost({ postId }).then(res => {
                const { text, info, comments } = res.data
                setViewText(text);
                setInfo(info);
                setCommentList(comments);
            })
        }
        viewAndText();
    }, [postId]);

    const update = useCallback(async (e, postId) => {
        const currentElement = e.target;
        setFlag(!flag);
        if (currentElement.textContent === 'UPDATE') {
            currentElement.textContent = 'FINISH';
        } else {
            currentElement.textContent = 'UPDATE';

            await updateData({
                postId,
                title,
                text: viewText
            });
        }
    }, [flag, title, viewText]);

    return (
        <div className="view">
            <div>
                <div>
                    post {n}
                    <div className="view-count">
                        views: {info.view}
                    </div>
                </div>
                <hr className="view-boundary" />
                <div>
                    {name}
                </div>
                <hr className="view-boundary" />
                <div>
                    {title}
                </div>
                <hr className="view-boundary" />
                <div className="view-text">
                    {
                        flag ?
                            <div>{viewText}</div> :
                            <textarea value={viewText} onChange={textChange} />
                    }
                </div>
                <Button style={{ position: "absolute", fontWeight: "bold" }} startIcon={<MdArrowBack />} className="back-button" onClick={() => history.goBack()}>BACK</Button>
                {
                    username === name ?
                        <div className="private-button">
                            <Button variant="contained" startIcon={<MdEdit />} onClick={e => update(e, postId)}>UPDATE</Button>
                            <Button variant="contained" startIcon={<MdDeleteOutline />} onClick={() => deleted(postId, history)}>DELETE</Button>
                        </div> : null
                }
            </div>
            <Recommend info={info} postId={postId} />
            <Reply commentList={commentList} postId={postId} />
        </div>
    )
}

export default View;
