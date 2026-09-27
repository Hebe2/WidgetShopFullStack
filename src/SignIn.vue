<script setup>
import { useRouter } from 'vue-router'
import { ref } from 'vue';
import axios from 'axios';
import { setSelectedCustomer } from './selectedCustomer';
const router = useRouter();
const customer = ref({
    username: '',
    password: ''
});

const customersApiUrl = 'http://localhost:8087/api/sign-in';

async function signInCustomer() {
    try {
        const response = await axios.post(customersApiUrl, customer.value);
        const firstName = typeof response.data === 'string'
            ? response.data
            : response.data?.firstName;
        setSelectedCustomer({
            username: customer.value.username,
            firstName: firstName || customer.value.username
        });
        router.push('/');
        console.log('Customer signed in successfully:', response.data);

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
            

                        <label>Username:</label><input v-model="customer.username" type="text" name="username" required/>
                        <label>Password</label><input v-model="customer.password" type="password" name="password" required/>

                        <button id="sign-in-button" type="submit">Sign In </button>
                        
                    </fieldset>
                </div>
            </form>
            
            <h3>If you don't have an account the you can <router-link  to="/create-account">Create Account</router-link></h3>
</template>

<style scoped>

.banner{
    text-align: center;
    margin-bottom: 4rem;
    background-color: lightgray;
    font-size: 1.8rem;
    padding:2px;
    border-block: black 2px solid;

}
</style>