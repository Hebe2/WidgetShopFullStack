<script setup>
import { computed, ref, onMounted } from 'vue';
import axios from 'axios';
import { setSelectedProduct } from '@/selectedProduct';
import { useRouter } from 'vue-router';
import { formatCurrency, formatNumber } from '@/formatter';
import { getSale } from '@/cart';


const router = useRouter();
const products = ref([]);
const categories = ref(new Array());

const BASE_URL = 'http://localhost:8087';
const productsApiUrl = `${BASE_URL}/api/products`;
const categoriesApiUrl = `${BASE_URL}/api/categories`;
const categoriesFilterApiUrl = ({ category }) => `${BASE_URL}/api/categories/${category}`;


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

function availableQuantity(product) {
    const quantityInCart = getSale().items
        .filter(item => item.product.productId === product.productId)
        .reduce((total, item) => total + Number(item.quantityPurchased), 0);

    return Math.max(0, Number(product.quantityInStock) - quantityInCart);
}

const availableProducts = computed(() =>
    products.value.filter(product => availableQuantity(product) > 0)
);

</script>
<template>
    <div class="banner">
        <h1>Products</h1>
    </div>
    <div id="product-body">
        <div class="category-buttons">
            <button @click.prevent="getProducts()">All</button>
            <button v-for="category in categories" :key="category" @click.prevent="filterByCategory(category)">{{
                category }}</button>
        </div>

        <div id="products-table-container">
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
                    <tr id="product-rows" v-for="product in availableProducts" :key="product.productId">
                        <td>{{ product.productId }}</td>
                        <td>{{ product.name }}</td>
                        <td>{{ product.description }}</td>
                        <td>{{ product.category }}</td>
                        <td>{{ formatCurrency(product.listPrice) }}</td>
                        <td>{{ formatNumber(availableQuantity(product), 0) }}</td>
                        <td><button id="buy-button" type="button" :disabled="availableQuantity(product) === 0"
                                @click.prevent="buyProduct(product)">Buy</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="nav">
            <router-link class="nav-button" to="/">Back to Home</router-link>
        </div>
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

#product-body {
    padding-block: 3rem;
    background-color: #eff7f1;
    height: 100%;
    font-family: "Isometra", serif;
}

.category-buttons {
    text-align: center;
    margin: 2em;
    
}

.category-buttons button {
    font-size: 1.5rem;
    padding: 10px;
    margin: 0 0.8rem;
    background-color: #D37A5A;
    color: #F7F4EF;
    border: #874d38 solid 2px;
    border-radius: 5px;
    padding: 10px;
    font-family: "Isometra", serif;
}

#products-table-container {
    margin-inline: auto;
    margin-bottom: 3rem;
    width: 80%;
    background-color: #F7F4EF;
    border: solid rgb(3, 74, 3) 3px;
    border-radius: 15px;
    overflow: hidden;
}

#products-table {
    width: 100%;
    font-size: 1rem;
    font-weight: bold;
    border-collapse: collapse;
}

#products-table td,
#products-table th {
    border-bottom: 2px dashed rgb(3, 74, 3);
    padding: 2rem;
    text-align: left;
}

#products-table tbody tr:last-child td {
    border-bottom: none;
}

#buy-button {
    font-weight: bold;
    font-size: 1.2rem;
    background-color: #D37A5A;
    color: #F7F4EF;
    border: #874d38 solid 2px;
    border-radius: 5px;
    padding: 10px;
    font-family: "Isometra", serif;

}

#buy-button:disabled {
    cursor: not-allowed;
    opacity: 0.6;
}

.nav {
    display: block;
    font-size: 1.5rem;
    text-align: center;
    margin-bottom: 5rem;
}

.nav-button {
    font-weight: bold;
    font-size: 1.2rem;
    background-color: #D37A5A;
    color: #F7F4EF;
    border: #874d38 solid 2px;
    border-radius: 5px;
    padding: 13px;
    text-decoration: none;
}
</style>
