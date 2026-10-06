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

.banner{
    text-align: center;
    margin-bottom: 4rem;
    background-color: lightgray;
    font-size: 1.8rem;
    padding:2px;
    border-block: black 2px solid;

} 

#products-container{
    display: grid;
    grid-template-columns: repeat(3, 25rem);
    grid-template-rows: repeat(1, 20rem);
    gap: 16px;
    justify-content: center;
}

.img-container{
    border: black 2px solid;
    width:100%;
    height:100%;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    display: flex;
    margin: auto;
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