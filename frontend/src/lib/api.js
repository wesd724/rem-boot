import axios from "axios";
import { LIST_LENGTH_PER_PAGE as length } from "../data/constant";

export const createData = data => {
    return axios.post(import.meta.env.VITE_REACT_APP_POST_URL, data);
}

export const readData = (page, search = "") => {
    return axios.get(import.meta.env.VITE_REACT_APP_POST_URL + `/s/${page}`, {
        params:
            { length, search }
    });
}

export const updateData = ({ postId, title, text }) => {
    return axios.put(import.meta.env.VITE_REACT_APP_POST_URL + `/${postId}`, { title, text });
}

export const deleteData = postId => {
    return axios.delete(import.meta.env.VITE_REACT_APP_POST_URL, {
        params: {
            postId
        }
    });
}

export const ViewPost = ({ postId }) => {
    return axios.get(import.meta.env.VITE_REACT_APP_POST_URL + `/${postId}`);
}

export const addReply = data => {
    return axios.post(import.meta.env.VITE_REACT_APP_COMMENT_URL, data);
}

export const updateOneReply = data => {
    return axios.patch(import.meta.env.VITE_REACT_APP_COMMENT_URL, data);
}

export const deleteOneReply = data => {
    return axios.delete(import.meta.env.VITE_REACT_APP_COMMENT_URL, { data });
}

export const recommend = data => {
    return axios.patch(import.meta.env.VITE_REACT_APP_POST_URL, data);
}

export const login = data => {
    return axios.post(import.meta.env.VITE_REACT_APP_LOGIN_URL, data);
}

export const register = data => {
    return axios.post(import.meta.env.VITE_REACT_APP_LOGIN_URL, + "/register", data);
}
