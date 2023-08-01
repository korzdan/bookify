import React, {useState} from 'react';
import cart from "../../assets/cart.png";
import {useSelector} from "react-redux";
import CartMenu from "../CartMenu/CartMenu";
import {useNavigate} from "react-router";

const CartBlock = () => {

    const [isCartMenuVisible, setIsCartMenuVisible] = useState(false);
    const items = useSelector(state => state.cart.itemsInCart)
    const totalPrice = useSelector(state => state.cart.totalPrice)
    const navigate = useNavigate();

    const toOrderPage = () => {
        navigate("/order")
    }

    return (
        <div className="inline-flex flex items-center justify-center pr-3">
            <img alt="Cart" className="h-7 w-7 hover:cursor-pointer"
                src={cart} onClick={() => setIsCartMenuVisible(!isCartMenuVisible)}/>
            {totalPrice > 0 ? <span className="cart-block__total-price">{totalPrice} BYN</span> : null}
            { isCartMenuVisible && <CartMenu items={items} totalPrice={totalPrice} onClick={() => toOrderPage()}/>}
        </div>
    );
};

export default CartBlock;
