import React, { useCallback, useEffect, useState } from "react";
import "./css/recommend.css";
import { IoMdThumbsUp, IoMdThumbsDown } from "react-icons/io";
import { recommend } from "../lib/api";

const Recommend = ({ info, postId }) => {
    const [goodPoint, setGoodPoint] = useState(info.good);
    const [badPoint, setBadPoint] = useState(info.bad);
    const [act, setAct] = useState(true);

    useEffect(() => {
        setGoodPoint(info.good);
        setBadPoint(info.bad);
    }, [info])

    const increaseGoodPoint = useCallback(() => {
        if (act) {
            recommend({ postId, recommend: true });
            setGoodPoint(good => good + 1);
            setAct(false);
        }
    }, [postId, act]);

    const increaseBadPoint = useCallback(() => {
        if (act) {
            recommend({ postId, recommend: false });
            setBadPoint(bad => bad + 1);
            setAct(false);
        }
    }, [postId, act]);

    return (
        <div className="good-bad">
            <div className="good">
                <IoMdThumbsUp onClick={increaseGoodPoint} />
                <div>
                    {goodPoint}
                </div>
            </div>
            <div className="bad">
                <IoMdThumbsDown onClick={increaseBadPoint} />
                <div>
                    {badPoint}
                </div>
            </div>
        </div>
    )
}

export default Recommend;
