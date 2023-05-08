import jwt from 'jwt-decode';

export const getToken = () => {
    return localStorage.getItem("token") || null;
}

export const setToken = (token) => {
    localStorage.setItem("token", token);
}

export const isAdmin = () => {
    if (!getToken()) {
        return false;
    } else {
        return getRoles(getToken())
            .some((obj) => obj.authority === "ROLE_ADMIN");
    }
}

export const isSuperAdmin = () => {
    if (!getToken()) {
        return false;
    } else {
        return getRoles(getToken())
            .some((obj) => obj.authority === "ROLE_SUPER_ADMIN");
    }
}


const getRoles = () => {
    return jwt(getToken()).roles;
}
