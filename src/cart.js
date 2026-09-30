import * as sessionStore from '@/sessionStorage';

export class SaleItem {

	product = null;
	quantityPurchased = null;
	salePrice = null;

	constructor(product, quantityPurchased, salePrice) {
		this.product = product;
		this.quantityPurchased = quantityPurchased;
		this.salePrice = salePrice;
	}

	getItemTotal() {
		return this.quantityPurchased * this.salePrice;
	}

}

export class Sale {

	customer = null;
	items = new Array();

	setCustomer(customer) {
		this.customer = customer;
	}

	addItem(item) {
		this.items.push(item);
	}

	getTotal() {
		let total = 0;
		for (const item of this.items) {
			total += item.getItemTotal();
		}
		return total;
	}

	save() {
		// save the sale in session storage
		sessionStore.put("sale", this);
	}

	static rehydrate(object) {
		const sale = new Sale();

		sale.setCustomer(object.customer);

		for (const item of object.items) {
			let saleItem = new SaleItem(item.product, item.quantityPurchased, item.salePrice);
			sale.addItem(saleItem);
		}

		return sale;
	}

  


}
let sale = null;

// get incomplete sale from session store
let object = sessionStore.get("sale");

if (object != null) {
	sale = Sale.rehydrate(object);	
} else {
	sale = new Sale();
}

export function getSale() {
	return sale;
}

export function clearCart() {
	sale.items = [];
	sessionStore.remove("sale");
}