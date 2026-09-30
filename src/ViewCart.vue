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
    <button id="checkout-button" type="button" @click="checkout">Checkout</button>


</template>

<style scoped>
.banner {
    text-align: center;
    margin-bottom: 4rem;
    background-color: lightgray;
    font-size: 1.8rem;
    padding: 2px;
    border-block: black 2px solid;

}

#cart-table {
    border-collapse: collapse;
    margin-bottom: 2rem;
    width:80%;
    margin-inline: auto;
}

#cart-table td,
#cart-table th {
    border-bottom: 2px dashed black;
    padding: 1rem;
    font-size: 1.2rem;
    text-align: left;
    
}

#checkout-button {
    display: block;
    margin-inline: auto;
    font-size: 1.5rem;
    padding: 5px;
    margin-bottom: 5rem;
}
</style>