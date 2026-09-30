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
                        <td>{{ product.quantityInStock }}</td>

                    </tr>
                </tbody>


            </table>
        </div>
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

<style scoped>
.banner {
    text-align: center;
    margin-bottom: 4rem;
    background-color: lightgray;
    font-size: 1.8rem;
    padding: 2px;
    border-block: black 2px solid;

}

#products-table {
    text-align: left;
    width: 60%;
    margin-inline: auto;
    font-size: 1.2rem;
    margin-bottom: 4rem;
}
#products-table th,
#products-table td {
    border-bottom: dashed 1px black;
}

#products-table th {
    font-weight: bold;
    width:20%;
}

#products-table td {
    padding-left: 3rem;
}

#buy-container{
    min-width: 15rem;
    max-width: 50rem;
    margin-inline: auto;
}

#buy-container legend{
        font-size: 1.5rem;

    font-weight: bold;
}

#buy-container label,#buy-container input{
    font-size: 1.2rem;
padding: 5px;
}
#add-to-cart-button{
    font-size: 1.2rem;
    padding: 5px;
    margin-left: 1rem;
}
</style>
