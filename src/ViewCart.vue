<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { setSelectedProducts } from './selectedProducts';
import { useRouter } from 'vue-router';

const router = useRouter();
const products = ref(new Array());
//const majors = ref(new Array());

const productsApiUrl = 'http://localhost:8085/api/products';
// const majorsApiUrl = 'http://localhost:8085/api/majors';
const productsFilterApiUrl = ({ id }) => `http://localhost:8085/api/products/${id}`;


onMounted( () => {
	getShoppingCart();
    // getMajors();
});

async function getShoppingCart() {
	try {
		let response = await axios.get(productsApiUrl);
		products.value = response.data;
	} catch (error) {
		console.error(error);
		alert('Error getting products');
	}
}

function checkOut(cart) {
    setSelectedProducts(cart);
    router.push("/confirmation");
}

// click handler for the major filter buttons
// async function filterByMajor(major) {
// 	const filterUrl = majorsFilterApiUrl({'major':major});
//     try {
// 		let response = await axios.get(filterUrl);
// 		students.value = response.data;
// 	} catch (error) {
// 		console.error(error);
// 		alert('Error getting students');
// 	}
// }

</script>

<template>
    <h1>Products</h1>
      
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

                    <tr v-for="product in products" :key="product.id">
                        <td>{{product.id}}</td>
                        <td>{{product.name}}</td>
                        <td>{{product.price}}</td>
                        <td>{{product.quantity}}</td>
                        <td>{{product.total}}</td>
                        <td><form  @submit.prevent="update(product)" ><button>Update</button></form>
                        </td>
                    </tr>
                   
                </tbody>
            </table>

    <router-link class="nav" to="/">Back to Home</router-link>
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