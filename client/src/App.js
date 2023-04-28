import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import "./index.css"
import LoginPage from "./pages/LoginPage/LoginPage";
import Navbar from "./components/Navbar";
import BooksPage from "./pages/BooksPage/BooksPage";
import {Provider} from "react-redux";
import {store} from "./redux"

function App() {
    return (
        <Provider store={store}>
            <div className="App">
                <BrowserRouter>
                    <Navbar/>
                    <Routes>
                        <Route exact path="/login"
                               element={<LoginPage/>}/>
                        <Route exact path="/books"
                               element={<BooksPage/>}/>
                    </Routes>
                </BrowserRouter>
            </div>
        </Provider>
    );
}

export default App;
