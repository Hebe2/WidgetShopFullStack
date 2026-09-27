<script setup>
import { useRouter } from 'vue-router'
import { clearSelectedCustomer, selectedCustomer } from './selectedCustomer'

const router = useRouter()

function signOut() {
    clearSelectedCustomer()
    router.push('/')
}
</script>

<template>
    <nav id="nav-bar">
        <template v-if="selectedCustomer">
            <router-link to="/">Home</router-link>
            <router-link to="/view">Browse Products</router-link>
            <router-link to="/cart">View Cart</router-link>
            <button type="button" @click="signOut">Sign Out</button>
            <p>Welcome, {{ selectedCustomer.firstName }}!</p>
        </template>
        <template v-else>
            <router-link to="/">Home</router-link>
            <router-link to="/sign-in">Sign In</router-link>
            <router-link to="/create-account">Create Account</router-link>
        </template>
    </nav>
  
  <main>
	  <router-view />
  </main>
</template>

<style scoped>

#nav-bar{
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 15px;
}

#nav-bar a,button{
    font-size: 1.5rem;
    padding-block:0.5rem;
    padding-inline: 0.8rem;
    margin-inline: 0.8em;
    color:black;
    border:solid black 2px;
    background-color: lightgray;
    text-decoration: none;
}

#nav-bar p{
    font-size: 1.5rem;
    padding-block:0.5rem;
    padding-inline: 0.8rem;
    margin-inline: 0.8em;
    color:black;
    text-decoration: none;
}

</style>
