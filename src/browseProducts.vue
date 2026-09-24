<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { setSelectedProduct } from './selectedProduct';
import { useRouter } from 'vue-router';

const router = useRouter();
const products = ref(new Array());
const categories = ref(new Array());

const productsApiUrl = 'http://localhost:8087/api/products';
const categoriesApiUrl = 'http://localhost:8087/api/categories';
const categoriesFilterApiUrl = ({category}) => `http://localhost:8087/api/categories/${category}`;


onMounted( () => {
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

function buy(product) {
    setSelectedProduct(product);
    router.push("/buy");
}

// click handler for the category filter buttons
async function filterByCategory(category) {
	const filterUrl = categoriesFilterApiUrl({'category':category});
    try {
		let response = await axios.get(filterUrl);
		products.value = response.data;
	} catch (error) {
		console.error(error);
		alert('Error getting products');
	}
}

</script>
<template>
    <div class = "banner">
                <h1>Products</h1>
            </div>
            <div class="category-buttons">
                <button @click.prevent="getProducts()">All</button>
                <button v-for="category in categories" @click.prevent="filterByCategory(category)">{{category}}</button>
            </div>
            
            <table  id = "products-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Available</th>
                    </tr>
                </thead>
                <tbody>
        
                     <tr id="product-rows" v-for="product in products" :key="product.id">
                        <td>{{product.id}}</td>
                        <td>{{product.name}}</td>
                        <td>{{product.description}}</td>
                        <td>{{product.category}}</td>
                        <td>{{product.listPrice}}</td>
                        <td>{{product.quantityInStock}}</td>
                        <td><form  @submit.prevent="buy(product)" ><button>Buy</button></form></td>

                    </tr>
                   
                </tbody>
            </table>
                <router-link class="nav" to="/">Back to Home</router-link>

</template>

<style scoped>
</style>
