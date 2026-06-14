// src/config.js
const LOCAL_API = "http://localhost:8080";
const PROD_API  = "https://blood-bridge-v23j.onrender.com";

const API_BASE_URL =
  process.env.NODE_ENV === "production" ? PROD_API : LOCAL_API;

if (typeof window !== "undefined") {
  window.API_BASE_URL = API_BASE_URL;  
}

export default API_BASE_URL;
