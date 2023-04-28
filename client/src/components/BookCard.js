import React from 'react';

const BookCard = (props) => {
    return (
        <div className="lg:w-1/4 md:w-1/2 p-4 w-full">
            <a className="block relative h-48 rounded overflow-hidden">
                <img alt="ecommerce" className="object-contain object-center w-full h-full block"
                     src={"http://localhost:8081/book/img/" + props.id + ".jpg"}/>
            </a>
            <div className="mt-4">
                <h3 className="text-gray-500 text-xs tracking-widest title-font mb-1">{props.genre}</h3>
                <h2 className="text-gray-900 title-font text-lg font-medium">{props.title}</h2>
                <h2 className="text-gray-900 title-font text-lg font-normal">{props.author}</h2>
                <p className="mt-1 font-semibold">{props.price} BYN</p>
            </div>
        </div>
    );
};

export default BookCard;
