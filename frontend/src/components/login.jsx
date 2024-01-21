import React, { useCallback, useRef, useState } from "react";
import { Link, useHistory } from "react-router-dom";
import { AiFillEye } from 'react-icons/ai';
import { login } from "../lib/api";
import "./css/login.css"

const Login = () => {
    const history = useHistory();
    const eye = useRef();
    const [name, setName] = useState("");

    const [password, setPassword] = useState('');

    const changeName = useCallback(e => setName(e.target.value), []);
    const changePassword = useCallback(e => setPassword(e.target.value), []);

    const submit = useCallback(e => {
        login({ name, password }).then(res => {
            const { status, message } = res.data;
            if (status) {
                sessionStorage.setItem("name", name);
                history.push('/board');
            } else {
                alert(message);
            }
        });
        e.preventDefault();
    }, [name, password, history]);

    const open = () => {
        if (eye.current.type === "password")
            eye.current.type = "text";
        else eye.current.type = "password";
    }

    return (
        <div className="login">
            <h3 style={{ marginLeft: "75px" }}>LOGIN</h3>
            <form onSubmit={submit}>
                <input type="text" placeholder="ID" onChange={changeName} required></input><br />
                <input type="password" placeholder="PASSWORD" onChange={changePassword} ref={eye} required></input><br />
                <button className="button login-button" type="submit">login</button>
                <div className="eye" onClick={open}>
                    <AiFillEye />
                </div>
            </form>
            <Link to='/register'><button className="button signup-button">sign up</button></Link>
            <button className="button anonymous-button" onClick={() => {
                sessionStorage.setItem("name", "--");
                history.push('/board');
            }}>anonymous</button>
        </div >
    )
}

export default Login;
