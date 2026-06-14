import React, { useState } from 'react';
import { login } from '../services/api';

export default function Login() {
  const [form, setForm] = useState({
    username: '',
    password: ''
  });

  const [msg, setMsg] = useState('');

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await login(form);

      localStorage.setItem(
        'token',
        response.data.token
      );

      setMsg('Login successful');
    } catch (error) {
      setMsg(
        error.response?.data?.message ||
        'Invalid credentials'
      );
    }
  };

  return (
    <div>
      <h2>Login</h2>

      <form onSubmit={handleSubmit}>
        <input
          name="username"
          placeholder="Username"
          value={form.username}
          onChange={handleChange}
          required
        />

        <input
          type="password"
          name="password"
          placeholder="Password"
          value={form.password}
          onChange={handleChange}
          required
        />

        <button type="submit">
          Login
        </button>
      </form>

      {msg && <p>{msg}</p>}
    </div>
  );
}