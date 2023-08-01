import React, {useEffect, useState} from 'react';
import axios from "axios";
import {getToken} from "../../utils/JwtToken";

const GeneralStatistics = () => {

    const [statistics, setStatistics] = useState(null);

    useEffect(() => {
        axios.get("http://localhost:8081/statistics", {
            headers: {
                'Authorization': 'Bearer ' + getToken()
            }
        }).then(response => {
                setStatistics(response.data);
            });
    }, []);

    if (!statistics) return null;

    return (
        <section className="text-gray-600 body-font px-10 mx-auto">
            <div className="container px-5 py-24 mx-auto">
                <div className="flex flex-col text-center w-full mb-10">
                    <h1 className="sm:text-3xl text-2xl font-medium title-font mb-4 text-gray-900">Почему стоит выбрать
                        именно нас?</h1>
                    <p className="lg:w-2/3 mx-auto leading-relaxed text-base">Bookify - молодой магазин книг с большим количетсвом всевозможных книг:
                        проза, поэзия, романы, детективы, ужасы, нехудожественная литература и многое другое можно найти на этом сайте.
                    </p>
                </div>
                <div className="container px-5 py-3 mx-auto">
                    <div className="flex flex-wrap -m-4 text-center justify-center">
                        <div className="p-4 sm:w-1/4 w-1/2">
                            <h2 className="title-font font-medium sm:text-4xl text-3xl text-gray-900">{statistics.usersNum}</h2>
                            <p className="leading-relaxed">Пользователей</p>
                        </div>
                        <div className="p-4 sm:w-1/4 w-1/2">
                            <h2 className="title-font font-medium sm:text-4xl text-3xl text-gray-900">{statistics.ordersNum}</h2>
                            <p className="leading-relaxed">Заказов</p>
                        </div>
                        <div className="p-4 sm:w-1/4 w-1/2">
                            <h2 className="title-font font-medium sm:text-4xl text-3xl text-gray-900">{statistics.booksNum}</h2>
                            <p className="leading-relaxed">Книг</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default GeneralStatistics;
