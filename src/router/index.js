import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/Home.vue'
import ViewProducts from '@/browseProducts.vue'
import BuyProducts from '@/buyProduct.vue'
import CreateAccount from '@/CreateAccount.vue'
import SignIn from '@/SignIn.vue'
import ViewCart from '@/ViewCart.vue'
import Thankyou from '@/Thankyou.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
	{ path: '/', component: Home },
  { path: '/create-account', component: CreateAccount },
  { path: '/sign-in', component: SignIn },
	{ path: '/view', component: ViewProducts },
  { path: '/add-to-cart', component: BuyProducts },
  { path: '/cart', component: ViewCart },
  { path: '/thank-you', component: Thankyou }
]
});

export default router
