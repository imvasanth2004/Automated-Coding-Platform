import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const LoginPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("http://localhost:8081/api/users/login", {
                email,
                password
            });

            // Save Token and Role to Local Storage (Browser Memory)
            localStorage.setItem("token", response.data.token);
            localStorage.setItem("role", response.data.role);
            localStorage.setItem("username", response.data.username);

            // Redirect based on Role
            if (response.data.role === 'ADMIN') {
                navigate("/add-problem"); // Admins go here
            } else {
                navigate("/dashboard"); // Students go here
            }

        } catch (err) {
            setError("Invalid Credentials. Please try again.");
        }
    };

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card shadow">
                        <div className="card-body">
                            <h3 className="text-center fw-bold">Login to Platform</h3>
                            {error && <div className="alert alert-danger">{error}</div>}
                            <form onSubmit={handleLogin}>
                                <div className="mb-3">
                                    <label>Email</label>
                                    <input type="email" className="form-control" 
                                        value={email} onChange={(e) => setEmail(e.target.value)} required />
                                </div>
                                <div className="mb-3">
                                    <label>Password</label>
                                    <input type="password" className="form-control" 
                                        value={password} onChange={(e) => setPassword(e.target.value)} required />
                                </div>
                                <button type="submit" className="btn btn-primary w-100">Login</button>
                            </form>
                            <div className="mt-3 text-center">
                                <a href="/register">New User? Register here</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginPage;