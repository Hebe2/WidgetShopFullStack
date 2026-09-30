
let selectedProduct = new Object();

export function setSelectedProduct(product) {
  selectedProduct = product;
}

export function getSelectedProduct() {
  return selectedProduct;
}