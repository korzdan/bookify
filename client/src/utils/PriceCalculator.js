export const calcTotalPrice = items => items.reduce((acc, book) => acc += book.price, 0);
