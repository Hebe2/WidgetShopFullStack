export function formatCurrency (value) {
	return new Intl.NumberFormat('en-NZ', {style: "currency", currency: 'NZD'}).format(value);
}

export function formatNumber (value, decimalPlaces) {
	return value.toFixed(decimalPlaces);
}