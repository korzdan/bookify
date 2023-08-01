import React, {useEffect, useState} from 'react';
import axios from "axios";
import {getToken} from "../../utils/JwtToken";

const PersonalAreaPage = () => {

    const [account, setAccount] = useState(null);

    useEffect(() => {
        axios.get("http://localhost:8081/user/my", {
            headers: {
                'Authorization': 'Bearer ' + getToken()
            }
        })
            .then(response => {
                setAccount(response.data);
            });
    }, []);

    if (!account) return null;

    return (
        <div>
            <div className="flex justify-center">
                <h2 className="text-lg leading-6 font-medium text-gray-900">
                    Личный кабинет
                </h2>
            </div>
            <div className="bg-gray-800 max-w-2xl shadow overflow-hidden sm:rounded-lg mx-auto mt-10 mb-14">
                <div className="px-4 py-5 sm:px-6 bg-gray-900">
                    <h3 className="text-white text-lg leading-6 font-medium">
                        Персональная информация
                    </h3>
                </div>
                <div className="border-t border-gray-200">
                    <dl>
                        <div className="px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                            <dt className="text-sm font-medium text-gray-500">
                                Имя
                            </dt>
                            <dd className="mt-1 text-sm text-white sm:mt-0 sm:col-span-2">
                                {account.name}
                            </dd>
                        </div>
                        <div className="px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                            <dt className="text-sm font-medium text-gray-500">
                                Фамилия
                            </dt>
                            <dd className="mt-1 text-sm text-white sm:mt-0 sm:col-span-2">
                                {account.surname}
                            </dd>
                        </div>
                        <div className="px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                            <dt className="text-sm font-medium text-gray-500">
                                Email
                            </dt>
                            <dd className="mt-1 text-sm text-white sm:mt-0 sm:col-span-2">
                                {account.email}
                            </dd>
                        </div>
                        <div className="px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                            <dt className="text-sm font-medium text-gray-500">
                                Количество заказов
                            </dt>
                            <dd className="mt-1 text-sm text-white sm:mt-0 sm:col-span-2">
                                {account.ordersNum}
                            </dd>
                        </div>
                    </dl>
                </div>
            </div>
        </div>
    );
};

export default PersonalAreaPage;
