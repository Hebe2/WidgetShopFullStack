<script setup>
import { ref } from 'vue';
import { clearCart, getSale } from '@/cart';

const sale = ref(getSale());

function checkout(sale) {
    const customer = sessionStore.get("customer");
    sale.value.setCustomer(customer);
    axios.post('http://localhost:8087/api/sales', sale.value);
    clearCart();
    router.push("/thank-you");
    console.log('checkout', sale);
}
</script>

<template>
    <div class="banner">
        <h1>Thank You For Your Order</h1>
    </div>
    <p>We have emailed you the details of your order to your registered email address.</p>
</template>

<style scoped>
.banner {
    text-align: center;
    margin-bottom: 2rem;
    background-color: lightgray;
    font-size: 1.8rem;
    padding: 2px;
    border-block: black 2px solid;

}
p{
    text-align: center;
    font-size: 1.5rem;
    margin-bottom: 5rem;
}
</style>