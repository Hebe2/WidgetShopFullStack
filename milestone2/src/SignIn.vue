<script setup>
import { useRouter } from 'vue-router'
import { ref } from 'vue';
import axios from 'axios';
import { signIn } from '@/signIn';
import { createToken } from '@/authentication';

const router = useRouter();
const customer = ref({
    username: '',
    password: ''
});

const BASE_URL = 'https://widgetshop-backend.onrender.com';
const customerByUsernameApiUrl = (username) => `${BASE_URL}/api/customers/${username}`;

async function signInCustomer() {
    try {
        createToken(customer.value.username, customer.value.password);
        const response = await axios.get(customerByUsernameApiUrl(customer.value.username));
        signIn(response.data);
        router.push('/');
    } catch (error) {
        console.error(error);
        alert('Failed to sign in customer');
    }
}

</script>

<template>
    <div class="banner">
        <h1>Sign In</h1>
    </div>

    <div id="signInBody">
        <form @submit.prevent="signInCustomer">
            <div id="sign-in-container">
                <fieldset id="account-details">

                    <legend>Account Details</legend>


                    <label>Username:</label><input v-model="customer.username" type="text" name="username" required />
                    <label>Password</label><input v-model="customer.password" type="password" name="password"
                        required />

                    <button id="sign-in-button" type="submit">Sign In </button>

                </fieldset>
            </div>
        </form>

        <h3>If you don't have an account the you can <router-link to="/create-account">Create Account</router-link></h3>
    </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Isometra&display=swap');
.banner{
    text-align: center;
    background-color: #9BBCA8;
    font-size: 1.8rem;
    padding:2px;
    border-bottom: rgb(3, 74, 3) 2px solid;
    color:#212A27;
    font-family: "Isometra", serif;
} 
#signInBody {
    padding-block: 4rem;
    background-color: #eff7f1;
    height: 50vh;
    font-family: "Isometra", serif;
}

#sign-in-container {
    max-width: 60rem;
    margin-inline: auto;
}

#account-details {
    display: grid;
    grid-template-columns: fit-content(10em) 1fr;
    gap: 1em;
    font-size: 1.2rem;
    font-weight: bold;
    background-color: #F7F4EF;
    border: solid  rgb(3, 74, 3) 3px;
    padding: 3rem;
    border-radius: 15px;
}


#account-details legend{
    color: rgb(3, 54, 3);
}

#account-details legend,
#sign-in-button {
    grid-column: 1 / -1;
    font-size: 1.2rem;
    font-weight: bold;
    
}

#account-details input {
    padding: 5px;
    border: solid rgb(3, 74, 3) 2px;
    border-radius: 5px;
    font-family: "Isometra", serif;
}
#sign-in-button {
    background-color: #D37A5A;
    color: #F7F4EF;
    border:#874d38 solid 2px;
    border-radius: 5px;
    padding:5px;
}

#sign-in-button:hover {
    background-color: #c37154;
}

#signInBody h3 {
    text-align: center;
    font-size: 1.2rem;
    margin-top: 2rem;
    color: rgb(3, 54, 3);

}
#signInBody h3 a {
    color: #c37154;
    text-decoration: underline;
}

</style>