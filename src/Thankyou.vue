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
    <div id="thank-you-body">
        <p>We have emailed you the details of your order to your registered email address.</p>
    </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Isometra&display=swap');

.banner {
    text-align: center;
    background-color: #9BBCA8;
    font-size: 1.8rem;
    padding: 2px;
    font-family: "Isometra", serif;
}

#thank-you-body {
    padding-block: 4rem;
    background-color: #eff7f1;
    height: 45vh;
    font-family: "Isometra", serif;
}
p {
    text-align: center;
    font-size: 1.5rem;
    margin-bottom: 5rem;
}
</style>