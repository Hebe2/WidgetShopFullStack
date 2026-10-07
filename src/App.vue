<script setup>
import { useRouter } from 'vue-router'
import { isSignedIn, signedInCustomer, signOut } from '@/signIn'

const router = useRouter()

function doSignOut() {
    signOut()
    router.push('/')
}
</script>

<template>
    <nav id="nav-bar">
        <template v-if="isSignedIn">
            <router-link to="/">Home</router-link>
            <router-link to="/view">Browse Products</router-link>
            <router-link to="/cart">View Cart</router-link>
            <a href="#" @click.prevent="doSignOut">Sign Out</a>
            <p>Welcome, {{ signedInCustomer.firstName }}!</p>
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
@import url('https://fonts.googleapis.com/css2?family=Isometra&display=swap');
#nav-bar{
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 15px;
    background-color: #1b2e24;
    height: 60px;
    font-family: "Isometra", serif;
}

#nav-bar a,
#nav-bar p,
#nav-bar button{
    font-size: 1.5rem;
    padding-inline: 0.8rem;
    margin-inline: 0.8em;
    color:#F7F4EF;
    background-color: #1b2e24;
    text-decoration: none;
}
#nav-bar a:hover,
#nav-bar button:hover {
    color:#D37A5A;
  
}
/* #nav-bar p{
    font-size: 1.5rem;
    padding-block:0.5rem;
    padding-inline: 0.8rem;
    margin-inline: 0.8em;
    text-decoration: none;
} */

</style>
