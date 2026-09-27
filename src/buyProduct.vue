<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { getSelectedProduct } from '@/selectedProduct';
import { SaleItem, getSale } from '@/cart';

const router = useRouter();
const product = ref(getSelectedProduct());
const quantity = ref(1);

function buyProduct() {
    // get the sale from the cart module
    let sale = getSale();

    // create a SaleItem using the data from the models
    let item = new SaleItem(product.value, quantity.value, product.value.listPrice);

    // add the item to the sale
    sale.addItem(item);

    // save the sale in the session storage
    sale.save();

    // display the cart component
    router.push("/cart");
}

</script>
<template>
    <div class="banner">
        <h1>How many do you want to buy?</h1>
    </div>


    <section v-if="product">
        <table id="products-table">
            <thead>
                <tr>
                    <th>ID:</th>
                    <th>Name:</th>
                    <th>Description:</th>
                    <th>Price:</th>
                    <th>Quantity available:</th>
                </tr>
            </thead>
            <tbody>

                <tr id="product-rows" :key="product.productId">
                    <td>{{ product.productId }}</td>
                    <td>{{ product.name }}</td>
                    <td>{{ product.description }}</td>
                    <td>{{ product.listPrice }}</td>
                    <td>{{ product.quantityInStock }}</td>
                </tr>

            </tbody>
        </table>

        <form @submit.prevent="buyProduct">
            <div id="buy-container">
                <fieldset id="buy-details">

                    <legend>Quantity to buy</legend>


                    <label>How many?</label><input v-model="quantity" type="number" name="quantitywanted" required />
                    <button id="add-to-cart-button" type="submit">Add to Cart</button>

                </fieldset>
            </div>
        </form>
    </section>

</template>

<style scoped></style>
