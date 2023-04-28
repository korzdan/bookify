import React from 'react';
import './CartItem.css';

const CartItem = ({book}) => {
    return (
        <div className="cart-item">
            <span>{book.title}</span>
            <div className="cart-item__price">
                <span>{book.price} BYN</span>
            </div>
        </div>
    );
};

export default CartItem;
