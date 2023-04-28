import React from 'react';
import logo from "../assets/logo.png";
import {Link} from "react-router-dom";
import CartBlock from "./CartBlock";

const Navbar = () => {

    return (
        <div>
            <header className="text-gray-600 body-font">
                <div className="container mx-auto flex flex-wrap p-5 flex-col md:flex-row items-center">
                    <a className="flex title-font font-medium items-center text-gray-900 mb-4 md:mb-0">
                        <img
                             className="w-10 h-10 text-white p-2 bg-indigo-500" alt="Logo"
                        src={logo}/>
                        <span className="ml-3 text-xl">Bookify</span>
                    </a>
                    <nav
                        className="md:mr-auto md:ml-4 md:py-1 md:pl-4 md:border-l md:border-gray-400	flex flex-wrap items-center text-base justify-center">
                        <Link to="/books" className="mr-5 hover:text-gray-900">Книги</Link>
                        <a className="mr-5 hover:text-gray-900">История заказов</a>
                        <a className="mr-5 hover:text-gray-900">Мои рекомендации</a>
                    </nav>
                    <CartBlock/>
                    <button
                        className="inline-flex items-center bg-gray-100 border-0 py-1 px-3 focus:outline-none hover:bg-gray-200
                         rounded text-base mt-4 md:mt-0">Войти
                        <svg fill="none" stroke="currentColor" strokeLinecap="round" strokeLinejoin="round"
                             strokeWidth="2" className="w-4 h-4 ml-1" viewBox="0 0 24 24">
                            <path d="M5 12h14M12 5l7 7-7 7"></path>
                        </svg>
                    </button>
                </div>
            </header>
        </div>
    );
};

export default Navbar;
