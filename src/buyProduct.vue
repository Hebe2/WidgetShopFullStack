<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { getSelectedProduct } from './selectedProduct';
import { useRouter } from 'vue-router';

const router = useRouter();
const selectedProduct = getSelectedProduct();
const products = ref(new Array());
const selectedQuantity = ref(1);

// const productsApiUrl = 'http://localhost:8087/api/products';
const productsFilterApiUrl = ({ id }) => `http://localhost:8087/api/products/${id}`;
// const categoriesApiUrl = 'http://localhost:8087/api/categories';
// const categoriesFilterApiUrl = ({ category }) => `http://localhost:8087/api/categories/${category}`;


onMounted(async() => {
    if(!selectedProduct){
        alert('No product selected');
        return;
    } 
    try{
        const response = await axios.get(
            // productsFilterApiUrl({selectedProduct.id })
        );
        products.value = response.data;
    } catch (error) {
        console.error(error);
        alert('Error getting product by ID');
    }
});


function addToCart(product) {
    console.log(`Adding ${selectedQuantity.value} of ${product.name} to cart`);
}

</script>
<template>
    <div class="banner">
        <h1>How many do you want to buy?</h1>
    </div>


    <table id="products-table">
        <thead>
            <tr>
                <th>ID:</th>
                <th>Name:</th>
                <th>Description:</th>
                <th>Price:</th>
                <th>Quantity Available:</th>
            </tr>
        </thead>
        <tbody>

            <tr id="product-rows" v-for="product in products" :key="product.id">
                <td>{{ product.id }}</td>
                <td>{{ product.name }}</td>
                <td>{{ product.description }}</td>
                <td>{{ product.category }}</td>
                <td>{{ product.listPrice }}</td>
                <td>{{ product.quantityInStock }}</td>
            </tr>
        </tbody>
    </table>
    <div id="quantity-box">
        <input type="number" v-model="selectedQuantity" min="1" :max="selectedProduct?.quantityInStock"/>
        <button @click.prevent="addToCart">Add to Cart</button>
    </div>

    <router-link class="nav" to="/">Back to Home</router-link>

</template>

<style scoped></style>
