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

    <div id="create-account-body">
        <form @submit.prevent="addCustomer">
            <div id="form-container">

                <fieldset id="account-details">

                    <legend>Account Details</legend>

                    <label>Username:</label><input v-model="customer.username" type="text" name="username" required />
                    <label>First Name:</label><input v-model="customer.firstName" type="text" name="first-name"
                        required />
                    <label>Last Name:</label><input v-model="customer.surname" type="text" name="last-name" required />
                    <label>Address:</label><textarea v-model="customer.shippingAddress" name="address"
                        required></textarea>
                    <label>Email:</label><input v-model="customer.emailAddress" type="email" name="email" required />
                    <label>Password</label><input v-model="customer.password" type="password" name="password"
                        required />

                    <button class="button" id="create-account-button" type="submit">Create Account</button>

                </fieldset>
            </div>
        </form>
        <div id="signin-button-container">
            <h3>Already have an account?
                <router-link class="nav" to="/sign-in">Sign In Here</router-link>
                </h3>
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

#create-account-body {
    padding-block: 4rem;
    background-color: #eff7f1;
    height: 100%;
    font-family: "Isometra", serif;
}

#form-container {
    min-width: 15rem;
    max-width: 50rem;
    margin-inline: auto;
}


#account-details {
    display: grid;
    grid-template-columns: fit-content(10em) 1fr;
    gap: 1em;
    font-size: 1.2rem;
    font-weight: bold;
    background-color: #F7F4EF;
    border: solid rgb(3, 74, 3) 3px;
    padding: 1.5rem;
    border-radius: 15px;
}

#account-details legend,
#account-details p {
    grid-column: 1 / -1;
    font-size: 1.2rem;
    font-weight: bold;
}

#account-details legend {
    color: rgb(3, 54, 3);
}
#account-details input{
    font-family: "Isometra", serif;
}

#create-account-button {
    grid-column: 1 / -1;
    justify-self: center;
    background-color: #D37A5A;
    color: #F7F4EF;
    border: #874d38 solid 2px;
    border-radius: 5px;
    padding: 10px;
    font-size: 1.2rem;
    font-weight: bold;
   
}

#signin-button-container {
    display: flex;
    justify-content: center;
    margin-top: 20px;

}

#signin-button-container h3 {
     text-align: center;
    font-size: 1.2rem;
    margin-top: 2rem;
    color: rgb(3, 54, 3);
}


#signin-button-container h3 a {
    color: #c37154;
    text-decoration: underline;
}
</style>