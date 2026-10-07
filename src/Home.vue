<script setup>
import butterSquishy from '@/assets/butterSquishy.jpeg';
import glowInTheDark from '@/assets/glowInTheDark.jpg';
import popFS from '@/assets/popFS.jpg';
import redFS from '@/assets/redFS.png';
import slowRise from '@/assets/slowRise.webp';
import triangleSpinner from '@/assets/triangleSpinner.jpg';


</script>

<template>  
	<div class="banner">
		<h1>Welcome to the WidgetShop</h1>
	</div>

	<div id="products-container">
		<div class="img-container">
			<img class="product-img" :src="butterSquishy" alt="BUTTER SQUISHY">
		</div>
		<div class="img-container">
			<img class="product-img" :src="glowInTheDark" alt="GLOW-IN-THE-DARK DUMPLING">
		</div>
        <div class="img-container">
			<img class="product-img" :src="slowRise" alt="SLOW RISE SQUISHY">
		</div>
		<div class="img-container">
			<img class="product-img" :src="triangleSpinner" alt="TRIANGLE SPINNER">
		</div>
        <div class="img-container">
			<img class="product-img" :src="popFS" alt="POP FIDGET SPINNER ">
		</div>
        <div class="img-container">
			<img class="product-img" :src="redFS" alt="RED FIDGET SPINNER ">
		</div>

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

#products-container{
    display: grid;
    grid-template-columns: repeat(3, 25rem);
    grid-template-rows: repeat(1, 20rem);
    gap: 32px;
    justify-content: center;
    padding-block: 5rem;
    background-color: #eff7f1;
    font-family: "Isometra", serif;
}

.img-container{
    border: rgb(3, 74, 3) 5px solid;
    width:100%;
    height:100%;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    display: flex;
    margin: auto;
    background-color: white;
    border-radius: 10px;
}


.product-img{
    width: 100%;
    height: 100%;
    object-fit: contain;

}

.footer-text{
    text-align:center;
}

</style>