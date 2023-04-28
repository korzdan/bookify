export const calcTotalPrice = (items) => {
    let price = items.reduce((acc, book) => acc += book.price, 0);
    return price.toString().substring(0, 5);
}
