import {Link} from "react-router-dom";
import React from "react";

export const handleBookNames = (order) => {
    const booksWithoutDuplicates = [];
    order.books.map(product => {
        const findItem = booksWithoutDuplicates.find(obj => obj.id === product.id);
        if (findItem) {
            findItem.count++;
        } else {
            booksWithoutDuplicates.push({
                ...product,
                count: 1,
            })
        }
    });
    return booksWithoutDuplicates.map((book) => (
        <p><Link className="font-medium hover:cursor-pointer"
                 to={"/books/" + book.id}>{book.title}</Link> - {book.count} шт.</p>
    ))
}
