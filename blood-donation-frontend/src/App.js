import React, { useState } from 'react';
import {
  BrowserRouter,
  Routes,
  Route,
  Link,
  Navigate
} from 'react-router-dom';

import DonorForm from './pages/DonorForm';
import DonorList from './pages/DonorList';
import Login from './pages/Login';
import Register from './pages/Register';

import './index.css';

function ProtectedRoute({ children }) {
  const token = localStorage.getItem('token');

  return token ? children : <Navigate to="/login" replace />;
}

export default function App() {
  const [showHelp, setShowHelp] = useState(false);

  const toggleHelp = () => {
    setShowHelp(prev => !prev);
  };

  const isLoggedIn = !!localStorage.getItem('token');

  const handleLogout = () => {
    localStorage.removeItem('token');
    window.location.href = '/login';
  };

  return (
    <BrowserRouter>
      <div className="app-container">

        <header className="app-header">
          <div className="app-title">Blood Bridge</div>

          <nav className="app-nav">

            {!isLoggedIn ? (
              <>
                <Link to="/login">Login</Link>
                <Link to="/signup">Sign Up</Link>
              </>
            ) : (
              <>
                <Link to="/">Donor List</Link>
                <Link to="/register-donor">Register Donor</Link>

                <button
                  onClick={handleLogout}
                  style={{
                    background: 'transparent',
                    border: 'none',
                    cursor: 'pointer'
                  }}
                >
                  Logout
                </button>
              </>
            )}

          </nav>

          <div className="help-icon" onClick={toggleHelp}>
            ?

            {showHelp && (
              <div className="help-tooltip">
                <div className="help-tooltip-title">
                  About this project
                </div>

                <p>
                  This app connects blood donors with people who need blood.
                </p>

                <p>
                  Built using React, Spring Boot, PostgreSQL,
                  Render and Netlify.
                </p>
              </div>
            )}
          </div>
        </header>

        <main className="app-main">
          <div className="app-card-stack">
            <div className="app-card">

              <Routes>

                <Route
                  path="/"
                  element={
                    <ProtectedRoute>
                      <DonorList />
                    </ProtectedRoute>
                  }
                />

                <Route
                  path="/register-donor"
                  element={
                    <ProtectedRoute>
                      <DonorForm />
                    </ProtectedRoute>
                  }
                />

                <Route
                  path="/login"
                  element={<Login />}
                />

                <Route
                  path="/signup"
                  element={<Register />}
                />

              </Routes>

            </div>
          </div>
        </main>

        <footer className="app-footer">
          Engineered with care by <span>Mayank</span>.
        </footer>

      </div>
    </BrowserRouter>
  );
}