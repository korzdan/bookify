import React, {useState} from 'react';
import {useNavigate} from "react-router";
import axios from "axios";
import {setToken} from "../../utils/JwtToken";
import logo from "../../assets/logo.png";

const RegistrationPage = () => {
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const handleRegistration = () => {
        setError(null);
        axios.post("http://localhost:8081/auth/register", {
            name: name,
            surname: surname,
            email: email,
            password: password
        }).then(response => {
            setToken(response.data.token);
            navigate("/main")
        }).catch(error => {
            if (error.response.status === 409) {
                setError("Пользователь с таким email уже существует.");
            }
        });
    }

    return (
        <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8 mt-10">
            <div className="sm:mx-auto sm:w-full sm:max-w-sm">
                <img
                    className="mx-auto h-10 w-auto"
                    src={logo}
                    alt="Bookify"
                />
                <h2 className="mt-5 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
                    Регистрация
                </h2>
            </div>
            <div className="mt-5 sm:mx-auto sm:w-full sm:max-w-sm">
                {(error !== "") && (<div className="mb-5 text-red-600 flex justify-center">{error}</div>)}
                <form className="space-y-6">

                    <div>
                        <label htmlFor="name" className="block text-sm font-medium leading-6 text-gray-900">
                            Ваше имя
                        </label>
                        <div className="mt-2">
                            <input
                                id="name"
                                name="name"
                                type="name"
                                value={name}
                                onChange={e => setName(e.target.value)}
                                required
                                className="px-2 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                            />
                        </div>
                    </div>

                    <div>
                        <label htmlFor="surname" className="block text-sm font-medium leading-6 text-gray-900">
                            Ваша фамилия
                        </label>
                        <div className="mt-2">
                            <input
                                id="surname"
                                name="surname"
                                type="surname"
                                value={surname}
                                onChange={e => setSurname(e.target.value)}
                                required
                                className="px-2 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                            />
                        </div>
                    </div>

                    <div>
                        <label htmlFor="email" className="block text-sm font-medium leading-6 text-gray-900">
                            Email
                        </label>
                        <div className="mt-2">
                            <input
                                id="email"
                                name="email"
                                type="email"
                                value={email}
                                onChange={e => setEmail(e.target.value)}
                                required
                                className="px-2 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                            />
                        </div>
                    </div>

                    <div>
                        <div className="flex items-center justify-between">
                            <label htmlFor="password" className="block text-sm font-medium leading-6 text-gray-900">
                                Пароль
                            </label>
                        </div>
                        <div className="mt-2">
                            <input
                                id="password"
                                name="password"
                                type="password"
                                value={password}
                                onChange={e => setPassword(e.target.value)}
                                required
                                className="px-2 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                            />
                        </div>
                    </div>

                    <div className="flex w-full justify-center rounded-md bg-gray-800 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-gray-900 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">
                        <input type="button" id="login" value="Зарегистрироваться" onClick={handleRegistration}/>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default RegistrationPage;
