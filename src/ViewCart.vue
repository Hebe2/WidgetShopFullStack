<script setup>
import { ref } from 'vue';
import { clearCart, getSale } from '@/cart';
import { formatCurrency, formatNumber } from '@/formatters';

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
    <button type="button" @click.prevent="checkout(sale)">Checkout</button>


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