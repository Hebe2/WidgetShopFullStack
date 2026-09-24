<script setup>
import { useRouter } from 'vue-router'
import { getSelectedCustomer } from './selectedCustomer';
import { ref, onMounted } from 'vue';
import axios from 'axios';
const router = useRouter();
const customer = ref(new Object());

const customersApiUrl = 'http://localhost:8087/api/customers';


onMounted(() => {
    customer.value = getSelectedCustomer();
});

async function addCustomer() {
    try {
        await axios.post(customersApiUrl, customer.value);
        router.push('/Home');

    } catch (error) {
        console.error(error);
        alert('Failed to save customer');
    }

}



</script>

<template>
    <div class="banner">
        <h1>Create Account</h1>
    </div>


    <form @submit.prevent="addCustomer">
        <div id="form-container">

            <fieldset id="account-details">

                <legend>Account Details</legend>

                    <label>Username:</label><input type="text" name="username" required />
                    <label>First Name:</label><input type="text" name="first-name" required />
                    <label>Last Name:</label><input type="text" name="last-name" required />
                    <label>Address:</label><textarea name="address" required></textarea>
                    <label>Email:</label><input type="text" name="email" required />
                    <label>Password</label><input type="password" name="password" required />

                    <button class="button" id="create-account-button" type="submit">Create Account</button>

            </fieldset>
        </div>
    </form>
    <div id="signin-button-container">
        <button class="button" id="createpage-signin-button">
          <router-link class="nav" to="/sign-in">Sign In</router-link>
        </button>
    </div>
</template>

<style scoped>
/* CSS for the component goes here */
</style>