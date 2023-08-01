import React from 'react';
import {Outlet} from "react-router";
import Navbar from "../components/Navbar/Navbar";

const NavbarLayout = () => {
    return (
        <div>
            <Navbar/>
            <div>
                <Outlet/>
            </div>
        </div>
    );
};

export default NavbarLayout;
