// src/services/api.js
import axios from 'axios';
import API_BASE_URL from '../config';

const api = axios.create({
  baseURL: API_BASE_URL,
});

export default api;

// Donors
export const fetchDonors = (params) => api.get('/donors', { params });
export const addDonor = (data) => api.post('/donors', data);
export const deleteDonor = (id) => api.delete(`/donors/${id}`);


// Requests (if/when you create these endpoints)
export const fetchRequests = (params) => api.get('/requests', { params });
export const addRequest = (data) => api.post('/requests', data);

//login and register
export const login = (credentials) =>
  api.post('/auth/login', credentials);

export const register = (userData) =>
  api.post('/auth/register', userData);

  api.interceptors.request.use(
    (config) => {
      const token = localStorage.getItem('token');

      if (token) {
        config.headers.Authorization = `Bearer ${token}`;
      }

      return config;
    },
    (error) => Promise.reject(error)
  );