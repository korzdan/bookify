import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import "./index.css"
import LoginPage from "./pages/LoginPage/LoginPage";
import BookCard from "./components/BookCard";
import Navbar from "./components/Navbar/Navbar";
import BooksPage from "./pages/BooksPage/BooksPage";

function App() {
  return (
    <div className="App">
        <Navbar/>
        <BrowserRouter>
            <Routes>
                <Route exact path="/login"
                       element={<LoginPage/>}/>
                <Route exact path="/"
                       element={<BooksPage/>}/>
            </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
