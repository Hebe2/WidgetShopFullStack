import { ref } from 'vue'

export const selectedCustomer = ref(null)

export function setSelectedCustomer(customer) {
  selectedCustomer.value = customer
}

export function getSelectedCustomer() {
  return selectedCustomer.value
}

export function clearSelectedCustomer() {
  selectedCustomer.value = null
}