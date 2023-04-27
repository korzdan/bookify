import jwt from 'jwt-decode';

export const getToken = () => {
    return localStorage.getItem("token") || null;
}

export const setToken = (token) => {
    localStorage.setItem("token", token);
}

export const getFirstRole = (token) => {
    return jwt(token).roles[0].authority;
}
