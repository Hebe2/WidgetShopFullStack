<script setup>
/* JavaScript for the component goes here */
</script>

<template>
	<div class="banner">
		<h1>Welcome to the WidgetShop</h1>
	</div>

	<div id="products-container">

		<div class="img-container">
			<img class="product-img" src="./images/widget.jpeg" alt="slimy widget">
		</div>
		<div class="img-container">
			<img class="product-img" src="./images/pop-widget.jpeg" alt="Description of image">
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
    grid-template-columns: repeat(2, 25rem);
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