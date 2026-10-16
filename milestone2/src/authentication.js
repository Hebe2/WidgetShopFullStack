import axios from 'axios';
import router from '@/router';
import { ref } from 'vue';
import * as sessionStore from '@/sessionStorage';

export const signInMessage = ref("Sign in to continue.");

export function createToken(username, password) {

	// create the BAA token
	let authToken = window.btoa(username + ":" + password);

	// store token in session storage
	sessionStore.put("authToken", authToken);
}

export function configureAxios() {

	// add request interceptor to Axios that will add the token to the headers
	axios.interceptors.request.use(
		(config) => {

			// get token from session storage
			const authToken = sessionStore.get("authToken");

			if (authToken != null) {
				// add token to default headers
				config.headers.Authorization = `Basic ${authToken}`;
			}

			return config;
		},
		(error) => Promise.reject(error)
	);
}