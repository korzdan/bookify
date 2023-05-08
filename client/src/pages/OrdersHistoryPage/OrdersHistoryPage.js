import React, {useEffect, useState} from 'react';
import axios from "axios";
import {getToken} from "../../utils/JwtToken";
import {handleBookNames} from "../../utils/OrderUtils";

const OrdersHistoryPage = () => {

    const [orders, setOrders] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8081/order", {
            headers: {
                'Authorization': 'Bearer ' + getToken()
            }
        })
            .then(response => {
                setOrders(response.data);
            });
    }, [orders]);

    const deliverOrder = (orderId) => {
        axios.post(`http://localhost:8081/order/deliver/${orderId}`, '', {
            headers: {
                'Authorization': 'Bearer ' + getToken()
            }
        });
    }

    const deliveringOrder = (orderId) => {
        axios.post(`http://localhost:8081/order/delivering/${orderId}`, '', {
            headers: {
                'Authorization': 'Bearer ' + getToken()
            }
        });
    }

    const handleStatus = (order) => {
        if (order.status.localeCompare("PENDING") === 0) {
            return <td className="px-6 py-4 hover:cursor-pointer"
                       onClick={() => deliveringOrder(order.id)}>
                На рассмотрении
            </td>
        } else if (order.status.localeCompare("DELIVERING") === 0) {
            return <td className="px-6 py-4 text-yellow-300 hover:cursor-pointer"
                       onClick={() => deliverOrder(order.id)}>
                Доставляется
            </td>
        } else {
            return <td className="px-6 py-4 text-green-600">Доставлен</td>
        }
    }

    if (!orders) return null;

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
                        <th scope="col" className="px-6 py-3">Почта заказчика</th>
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
                            <td className="px-6 py-4">{order.user.email}</td>
                            <th scope="row"
                                className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">{handleBookNames(order)}</th>
                            <td className="px-6 py-4">{order.totalPrice} BYN</td>
                            <td className="px-6 py-4">{order.address}</td>
                            <td className="px-6 py-4">{order.comment}</td>
                            <td className="">{handleStatus(order)}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default OrdersHistoryPage;
