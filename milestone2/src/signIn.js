import { ref } from 'vue';

import { clearCart } from '@/cart';
import * as sessionStore from '@/sessionStorage';

// model for nav menu
export const isSignedIn = ref(!!sessionStore.get("customer"));

// model for signed in customer
export const signedInCustomer = ref(sessionStore.get("customer"));

export function signIn(customer) {
  sessionStore.put("customer", customer);
  signedInCustomer.value = customer;
  isSignedIn.value = true;
}

export function signOut() {
  isSignedIn.value = false;
  signedInCustomer.value = null;
  sessionStore.remove("customer");
  sessionStore.remove("authToken");
  clearCart();
}