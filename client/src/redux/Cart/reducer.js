import {createSlice} from "@reduxjs/toolkit";

const cartSlice = createSlice({
    name: "cart",
    initialState: {
        itemsInCart: [],
        totalPrice: 0
    },
    reducers: {
        setItemInCart: (state, action) => {
            const findItem = state.itemsInCart.find(obj => obj.id === action.payload.id)
            if (findItem) {
                findItem.count++;
            } else {
                state.itemsInCart.push({
                    ...action.payload,
                    count: 1,
                })
            }

            state.totalPrice = parseFloat(state.itemsInCart.reduce((sum, obj) => {
                return (obj.price * obj.count) + sum;
            }, 0).toString().substring(0, 6))
        },
        deleteItemFromCart: (state, action) => {
            const findItem = state.itemsInCart.find(obj => obj.id === action.payload.id)
            if (findItem && findItem.count > 1) {
                findItem.count--;
            }
            if (findItem.count === 1) {
                removeObjectWithId(state.itemsInCart, findItem.id)
            }
            state.totalPrice = parseFloat(state.itemsInCart.reduce((sum, obj) => {
                return (obj.price * obj.count) + sum;
            }, 0).toString().substring(0, 6))
        },
    }
})

const removeObjectWithId = (arr, id) => {
    const objWithIdIndex = arr.findIndex((obj) => obj.id === id);

    if (objWithIdIndex > -1) {
        arr.splice(objWithIdIndex, 1);
    }

    return arr;
}

export const {setItemInCart, deleteItemFromCart} = cartSlice.actions
export default cartSlice.reducer;
