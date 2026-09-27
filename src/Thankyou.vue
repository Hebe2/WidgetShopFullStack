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
/* centre the major buttons
    #majors {
        display: block;
        text-align: center;
    }

    /* add some gaps between the major buttons */
/* #majors button {
        margin-left: 2pt;
        margin-right: 2pt;
    } */
</style>