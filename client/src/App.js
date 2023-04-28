import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import "./index.css"
import LoginPage from "./pages/LoginPage/LoginPage";
import Navbar from "./components/Navbar";
import BooksPage from "./pages/BooksPage/BooksPage";
import {Provider} from "react-redux";
import {store} from "./redux"
import {getToken} from "./utils/JwtToken";
import BookPage from "./pages/BookPage";

function App() {

    const isAuthenticated = () => {
        return getToken() !== null;
    }

    return (
        <Provider store={store}>
            <div className="App">
                <BrowserRouter>
                    <Navbar/>
                    {
                        !isAuthenticated() ?
                            <Routes>
                                <Route exact path="/"
                                       element={<LoginPage/>}/>
                                <Route exact path="/registration"
                                       element={<LoginPage/>}/>
                            </Routes>
                            :
                            <Routes>
                                <Route exact path="/login"
                                       element={<LoginPage/>}/>
                                <Route exact path="/books"
                                       element={<BooksPage/>}/>
                                <Route path="/books/:id"
                                       element={<BookPage/>}/>
                            </Routes>
                    }
                </BrowserRouter>
            </div>
        </Provider>
    );
}

export default App;
