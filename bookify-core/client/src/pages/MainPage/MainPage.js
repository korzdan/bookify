import React from 'react';
import BooksPage from "../BooksPage/BooksPage";
import GeneralStatistics from "../../components/GeneralStatistics/GeneralStatistics";

const MainPage = () => {
    return (
        <div>
            <GeneralStatistics/>
            <BooksPage/>
        </div>
    );
};

export default MainPage;
