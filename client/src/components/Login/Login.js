import React, {useEffect, useState} from 'react';
import axios from "axios";
import {setToken} from "../../utils/JwtToken/JwtToken";
import "../Login/Login.css"

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);

    const handleLogin = () => {
        setError(null);
        axios.post("http://localhost:8081/auth/login", {
            email: email,
            password: password
        }).then(response => {
            setToken(response.data.token);
        }).catch(error => {
            if (error.response.status === 403) {
                setError("Вы ввели неверные данные.");
            } else {
                setError("Что-то пошло не так, попробуйте снова позже.");
            }
        });
    }

    return (
        <div className="outer-form">
            <form className="login-form">
                <div className="form-inner">
                    <h2>Войдите в свой аккаунт.</h2>
                    {(error !== "") && (<div className="login-error">{error}</div>)}
                    <div className="form-group">
                        <label htmlFor="username">Email:</label>
                        <input className="login" id="username" type="text" value={email}
                               onChange={e => setEmail(e.target.value)}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Пароль:</label>
                        <input className="login" type="password" id="password" value={password}
                               onChange={e => setPassword(e.target.value)}/>
                    </div>
                    <input className="login" type="button" id="login" value="Login" onClick={handleLogin}/>
                </div>
            </form>
        </div>
    );
};

export default Login;
