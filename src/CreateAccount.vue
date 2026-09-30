<script setup>
import { useRouter } from 'vue-router'
import { ref } from 'vue';
import axios from 'axios';
const router = useRouter();
const customer = ref({
    username: '',
    firstName: '',
    surname: '',
    shippingAddress: '',
    emailAddress: '',
    password: ''
});

const customersApiUrl = 'http://localhost:8087/api/register';

async function addCustomer() {
    try {
        await axios.post(customersApiUrl, customer.value);
        router.push('/');

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

                    <label>Username:</label><input v-model="customer.username" type="text" name="username" required />
                    <label>First Name:</label><input v-model="customer.firstName" type="text" name="first-name" required />
                    <label>Last Name:</label><input v-model="customer.surname" type="text" name="last-name" required />
                    <label>Address:</label><textarea v-model="customer.shippingAddress" name="address" required></textarea>
                    <label>Email:</label><input v-model="customer.emailAddress" type="email" name="email" required />
                    <label>Password</label><input v-model="customer.password" type="password" name="password" required />

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

.banner{
    text-align: center;
    margin-bottom: 4rem;
    background-color: lightgray;
    font-size: 1.8rem;
    padding:2px;
    border-block: black 2px solid;

}

#form-container{
    min-width: 15rem;
    max-width: 50rem;
    margin-inline: auto;
}
#account-details{
    display: grid;
    gap: 0.5em;
    grid-template-columns:fit-content(10em) 1fr;
}

#account-details legend,
#account-details p{
    grid-column: 1 / -1;
}

.button{
    font-size: 1rem;
    padding:5px;
}

#signin-button-container{
  display: flex;
  justify-content: center; 
  margin-top:20px;
}


</style>