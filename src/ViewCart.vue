<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { clearCart, getSale } from '@/cart';
import { formatCurrency, formatNumber } from '@/formatter';
import * as sessionStore from '@/sessionStorage';

const router = useRouter();
const sale = ref(getSale());

async function checkout() {
    if (sale.value.items.length === 0) {
        return;
    }

    try {
        const customer = sessionStore.get("customer");
        sale.value.setCustomer(customer);
        await axios.post('http://localhost:8087/api/sales', sale.value);
        clearCart();
        router.push("/thank-you");
    } catch (error) {
        console.error(error);
        alert('Failed to complete checkout');
    }
}
</script>

<template>
   <div class="banner">
        <h1>Shopping Cart</h1>
    </div>
    <div id="cart-body">
     
    <table id="cart-table">
        <thead>
            <tr>
                <th>Product</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="item in sale.items" :key="item.product.productId">
                <td>{{ item.product.name }}</td>
                <td>{{ formatCurrency(item.product.listPrice) }}</td>
                <td>{{ formatNumber(item.quantityPurchased, 2) }}</td>
                <td>{{ formatCurrency(item.getItemTotal()) }}</td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <th colspan="3">Total</th>
                <td>{{ formatCurrency(sale.getTotal()) }}</td>
            </tr>
        </tfoot>
    </table>
    <p v-if="sale.items.length === 0" id="empty-cart-message">Your cart is empty.</p>
    <button id="checkout-button" type="button" :disabled="sale.items.length === 0" @click="checkout">Checkout</button>
</div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Isometra&display=swap');

.banner {
    text-align: center;
    background-color: #9BBCA8;
    font-size: 1.8rem;
    padding: 2px;
    border-bottom: rgb(3, 74, 3) 2px solid;
    color: #212A27;
    font-family: "Isometra", serif;
}
#cart-body {
   padding-block: 4rem;
    background-color: #eff7f1;
    height: 100%;
    font-family: "Isometra", serif;
}

#cart-table {
    margin-bottom: 2rem;
    width:80%;
    text-align: left;
    table-layout: fixed;
    margin-inline: auto;
    border: solid rgb(3, 74, 3) 2px;
    border-radius: 15px;
    background-color: #F7F4EF;
}
#cart-table th{
    color:#212A27;
}

#cart-table td,
#cart-table th {
border-bottom: 2px dashed rgb(3, 74, 3);
    padding: 1rem;
    font-size: 1.2rem;
   
}

#cart-table tfoot tr:last-child td,
#cart-table tfoot tr:last-child th {
    border-bottom: none;
}

#checkout-button {
    display: block;
    margin-inline: auto;
    font-size: 1.5rem;
    margin-bottom: 5rem;
    font-weight: bold;
    background-color: #D37A5A;
    color: #F7F4EF;
    border: #874d38 solid 2px;
    border-radius: 5px;
    padding-block: 10px;
    padding-inline: 20px;
    text-decoration: none;
}

#checkout-button:disabled {
    cursor: not-allowed;
    opacity: 0.6;
}

#empty-cart-message {
    text-align: center;
    font-size: 1.2rem;
}
</style>