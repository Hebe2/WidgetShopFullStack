<script setup></script>

<template>
  <nav>
	  <router-link to="/">Home</router-link>
	  <router-link to="/view">Browse Products</router-link>
	  <!-- <router-link to="/a">Add Product</router-link> -->
  </nav>
  <main>
	  <router-view />
  </main>
</template>

<style scoped>
nav > a {
    padding: 5px;
}
</style>
