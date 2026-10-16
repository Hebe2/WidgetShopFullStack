/* Add key/value to session storage */
export function put(key, value) {
  sessionStorage.setItem(key, JSON.stringify(value));
}

/* Get value for given key from session storage */
export function get(key) {
  const value = sessionStorage.getItem(key);
  return value != null ? JSON.parse(value) : null;
}

/* Remove given key/value from session storage */
export function remove(key) {
  sessionStorage.removeItem(key);
}

/* Clear entire session */
export function clearSession() {
  sessionStorage.clear();
}