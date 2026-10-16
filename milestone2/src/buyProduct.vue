<script setup>
import { computed, ref } from 'vue';
import { useRouter } from 'vue-router';
import { getSelectedProduct } from '@/selectedProduct';
import { SaleItem, getSale } from '@/cart';

const router = useRouter();
const product = ref(getSelectedProduct());
const quantity = ref(1);
const sale = ref(getSale());
const quantityError = ref('');

const remainingStock = computed(() => {
    if (!product.value) return 0;
    const alreadyInCart = sale.value.items
        .filter(item => item.product.productId === product.value.productId)
        .reduce((total, item) => total + Number(item.quantityPurchased), 0);
    return Math.max(0, Number(product.value.quantityInStock) - alreadyInCart);
});

function buyProduct() {
    const requestedQuantity = Number(quantity.value);
    if (!Number.isInteger(requestedQuantity) || requestedQuantity < 1 || requestedQuantity > remainingStock.value) {
        quantityError.value = `Enter a whole number from 1 to ${remainingStock.value}.`;
        return;
    }

    const item = new SaleItem(product.value, requestedQuantity, product.value.listPrice);
    sale.value.addItem(item);
    sale.value.save();
    router.push("/cart");
}

</script>
<template>
    <div class="banner">
        <h1>How many do you want to buy?</h1>
    </div>

    <div id="buy-body">
        <section v-if="product">
            <div id="product-details" :key="product.productId">
                <table id="products-table">
                    <tbody>
                        <tr>
                            <th>ID:</th>
                            <td>{{ product.productId }}</td>
                        </tr>
                        <tr>
                            <th>Name:</th>
                            <td>{{ product.name }}</td>
                        </tr>
                        <tr>
                            <th>Description:</th>
                            <td>{{ product.description }}</td>
                        </tr>
                        <tr>
                            <th>Price:</th>
                            <td>{{ product.listPrice }}</td>
                        </tr>
                        <tr>
                            <th>Category:</th>
                            <td>{{ product.category }}</td>
                        </tr>
                        <tr>
                            <th>Quantity available:</th>
                            <td>{{ remainingStock }}</td>

                        </tr>
                    </tbody>


                </table>
            </div>
            <form @submit.prevent="buyProduct" novalidate>
                <div id="buy-container">
                    <fieldset id="buy-details">

                        <legend>Quantity to buy</legend>

                        <label>How many?</label><input v-model.number="quantity" type="number" name="quantitywanted"
                            min="1" :max="remainingStock" step="1" required @input="quantityError = ''" />
                        <button id="add-to-cart-button" type="submit" :disabled="remainingStock < 1">Add to Cart</button>
                        <p v-if="quantityError" class="quantity-error" role="alert">{{ quantityError }}</p>
                        <p v-else-if="remainingStock < 1" class="quantity-error" role="status">
                            No more stock of this product is available to add.
                        </p>

                    </fieldset>
                </div>
            </form>
        </section>
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

#buy-body {
    padding-block: 4rem;
    background-color: #eff7f1;
    height: 100%;
    font-family: "Isometra", serif;
}

#products-table {
    text-align: left;
    width: 60%;
    table-layout: fixed;
    margin-inline: auto;
    margin-bottom: 4rem;
    border: solid rgb(3, 74, 3) 2px;
    border-radius: 15px;
        background-color: #F7F4EF;

}

#products-table th,
#products-table td {
    border-bottom: 2px dashed rgb(3, 74, 3);
    padding: 1rem;
    font-size: 1.2rem;
    text-align: left;
}

#products-table th {
    font-weight: bold;
    width: 40%;
}

#products-table td {
    width: 60%;
    padding-left: 3rem;
}

#products-table tbody tr:last-child td,
#products-table tbody tr:last-child th {
    border-bottom: none;
}

#buy-details {
    border: solid rgb(3, 74, 3) 3px;
    border-radius: 15px;
    background-color: #F7F4EF;
    padding: 1.5rem;
}

#buy-container {
    min-width: 15rem;
    max-width: 54rem;
    margin-inline: auto;
}

#buy-container legend {
    font-size: 1.5rem;
    font-weight: bold;
    color: rgb(3, 54, 3);

}

#buy-container input {
    font-size: 1rem;
    padding: 5px;
    font-family: "Isometra", serif;
}

#buy-container label{
        font-weight: bold;
         padding: 5px;
        font-size: 1.3rem;
    margin-right: 1rem;
}

#add-to-cart-button {
    font-weight: bold;
    font-size: 1rem;
    background-color: #D37A5A;
    color: #F7F4EF;
    border: #874d38 solid 2px;
    border-radius: 5px;
    padding: 8px;
    text-decoration: none;
    margin-left: 1.5rem;
}

#add-to-cart-button:disabled {
    cursor: not-allowed;
    opacity: 0.6;
}

.quantity-error {
    color: #874d38;
    font-weight: bold;
}
</style>
