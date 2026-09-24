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

async function signInCustomer() {
    try {
        await axios.post(`${customersApiUrl}/sign-in`, customer.value);
        router.push('/Home');

    } catch (error) {
        console.error(error);
        alert('Failed to sign in customer');
    }

}

</script>

<template>
    <div class ="banner">
                <h1>Sign In</h1>
            </div>

            <form @submit.prevent="signInCustomer">
                <div id ="sign-in-container">
                    <fieldset id="account-details">

                        <legend>Account Details</legend>
            

                        <label>Username:</label><input type="text" name="username" required/>
                        <label>Password</label><input type="password" name="password" required/>

                        <button id="sign-in-button" type="submit">Sign In </button>
                        
                    </fieldset>
                </div>
            </form>
            
            <h3>If you don't have an account the you can   <router-link  to="/create-account">Create Account</router-link></h3>
</template>

<style scoped>
/* CSS for the component goes here */
</style>