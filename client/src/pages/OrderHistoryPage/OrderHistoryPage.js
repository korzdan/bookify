import React, {useEffect, useState} from 'react';
import axios from "axios";
import {getToken} from "../../utils/JwtToken";
import {handleBookNames} from "../../utils/OrderUtils";

const OrderHistoryPage = () => {

    const [orders, setOrders] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8081/order/my", {
            headers: {
                'Authorization': 'Bearer ' + getToken()
            }
        }).then(response => {
            setOrders(response.data);
            console.log(response.data)
        });
    }, []);

    const handleStatus = (order) => {
        if (order.status.localeCompare("PENDING") === 0) {
            return <td className="px-6 py-4">
                На рассмотрении
            </td>
        } else if (order.status.localeCompare("DELIVERING") === 0) {
            return <td className="px-6 py-4 text-yellow-300">
                Доставляется
            </td>
        } else {
            return <td className="px-6 py-4 text-green-600">Доставлен</td>
        }
    }

    return (
        <div>
            <div className="flex justify-center">
                <h2 className="text-xl font-medium text-gray-900 mb-5">
                    История заказов
                </h2>
            </div>
            <div className="relative overflow-x-auto shadow-md sm:rounded-lg mx-20 mt-5">
                <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                    <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                    <tr>
                        <th scope="col" className="px-6 py-3">Номер заказа</th>
                        <th scope="col" className="px-6 py-3">Книги</th>
                        <th scope="col" className="px-6 py-3">Общая цена</th>
                        <th scope="col" className="px-6 py-3">Адрес доставки</th>
                        <th scope="col" className="px-6 py-3">Комментарий</th>
                        <th scope="col" className="px-6 py-3">Статус заказа</th>
                    </tr>
                    </thead>
                    <tbody>
                    {orders.map((order) => (
                        <tr className="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                            <td className="px-6 py-4">{order.id}</td>
                            <th scope="row"
                                className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">{handleBookNames(order)}</th>
                            <td className="px-6 py-4">{order.totalPrice} BYN</td>
                            <td className="px-6 py-4">{order.address}</td>
                            <td className="px-6 py-4">{order.comment}</td>
                            {handleStatus(order)}
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default OrderHistoryPage;
