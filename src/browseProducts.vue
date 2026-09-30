<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { setSelectedProduct } from '@/selectedProduct';
import { useRouter } from 'vue-router';
import { formatCurrency, formatNumber } from '@/formatter';


const router = useRouter();
const products = ref([]);
const categories = ref(new Array());


const productsApiUrl = 'http://localhost:8087/api/products';
const categoriesApiUrl = 'http://localhost:8087/api/categories';
const categoriesFilterApiUrl = ({ category }) => `http://localhost:8087/api/categories/${category}`;


onMounted(() => {
    getProducts();
    getCategories();
});


async function getProducts() {
    try {
        let response = await axios.get(productsApiUrl);
        products.value = response.data;
    } catch (error) {
        console.error(error);
        alert('Error getting products');
    }
}

async function getCategories() {
    try {
        let response = await axios.get(categoriesApiUrl);
        categories.value = response.data;
    } catch (error) {
        console.error(error);
        alert('Error getting categories');
    }
}

// click handler for the category filter buttons
async function filterByCategory(category) {
    const filterUrl = categoriesFilterApiUrl({ 'category': category });
    try {
        let response = await axios.get(filterUrl);
        products.value = response.data;
    } catch (error) {
        console.error(error);
        alert('Error getting products');
    }
}

function buyProduct(product) {
    setSelectedProduct(product);
    router.push("/add-to-cart");
}


</script>
<template>
    <div class="banner">
        <h1>Products</h1>
    </div>
    <div class="category-buttons">
        <button @click.prevent="getProducts()">All</button>
        <button v-for="category in categories" :key="category"
            @click.prevent="filterByCategory(category)">{{ category }}</button>
    </div>

    <table id="products-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Category</th>
                <th>Price</th>
                <th>Available</th>
                <th></th>
            </tr>
        </thead>
        <tbody>

            <tr id="product-rows" v-for="product in products" :key="product.productId">
                <td>{{ product.productId }}</td>
                <td>{{ product.name }}</td>
                <td>{{ product.description }}</td>
                <td>{{ product.category }}</td>
                <td>{{ formatCurrency(product.listPrice) }}</td>
                <td>{{ formatNumber(product.quantityInStock, 0) }}</td>
                <td><button id = "buy-button" type="button" @click.prevent="buyProduct(product)">Buy</button></td>


            </tr>

        </tbody>
    </table>
    <div class="nav">
    <router-link class="nav-button" to="/">Back to Home</router-link>
    </div>

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

.category-buttons {
    text-align: center;
    margin: 2em;
}

.category-buttons button {
    font-size: 1.5rem;
    padding: 5px;
    margin: 0 0.5rem;
}

#products-table {
    margin-inline: auto;
    border-collapse: collapse;
    margin-bottom: 3rem;
    width: 80%;
}

#products-table td,
#products-table th {
    border-block: 2px dashed black;
    padding: 2rem;
    font-size: 1.2rem;
    text-align: left;
}

#buy-button {
    font-weight: 400;
    font-size: 1.2rem;
    padding: 5px;
}

.nav {
    display: block;
    font-size: 1.5rem;
    text-align: center;
    margin-bottom: 5rem;
}

.nav-button {
    text-decoration: none;
    color: black;
    border: 2px solid black;
    padding: 8px;
    background-color: lightgray;
}
</style>
