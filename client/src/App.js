import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import "./index.css"
import LoginPage from "./pages/LoginPage/LoginPage";
import BooksPage from "./pages/BooksPage/BooksPage";
import {Provider} from "react-redux";
import {store} from "./redux"
import NavbarLayout from "./utils/NavbarLayout";
import RegistrationPage from "./pages/RegistrationPage/RegistrationPage";
import BookPage from "./pages/BookPage/BookPage";
import PersonalAreaPage from "./pages/PersonalAreaPage/PerosnalAreaPage";
import OrderPage from "./pages/OrderPage/OrderPage";
import OrderHistoryPage from "./pages/OrderHistoryPage/OrderHistoryPage";
import OrdersHistoryPage from "./pages/OrdersHistoryPage/OrdersHistoryPage";
import BookStatisticsPage from "./pages/BookStatisticsPage/BookStatisticsPage";
import BookCreatePage from "./pages/BookCraetePage/BookCreatePage";
import MainPage from "./pages/MainPage/MainPage";
import NewAdminPage from "./pages/NewAdminPage/NewAdminPage";

function App() {

    return (
        <Provider store={store}>
            <div className="App">
                <BrowserRouter>
                    <Routes>
                        <Route path="/" element={<LoginPage/>}/>
                        <Route path="/registration" element={<RegistrationPage/>}/>
                        <Route element={<NavbarLayout/>}>
                            <Route path="/main" element={<MainPage/>}/>
                            <Route path="/books" element={<BooksPage/>}/>
                            <Route path="/books/:id" element={<BookPage/>}/>
                            <Route path="/personal-area" element={<PersonalAreaPage/>}/>
                            <Route path="/order" element={<OrderPage/>}/>
                            <Route path="/history" element={<OrderHistoryPage/>}/>
                            <Route path="/orders" element={<OrdersHistoryPage/>}/>
                            <Route path="/statistics" element={<BookStatisticsPage/>}/>
                            <Route path="/new-book" element={<BookCreatePage/>}/>
                            <Route path="/new-admin" element={<NewAdminPage/>}/>
                        </Route>
                    </Routes>
                </BrowserRouter>
            </div>
        </Provider>
    );
}

export default App;
