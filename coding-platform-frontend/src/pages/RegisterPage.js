import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const RegisterPage = () => {
    const [user, setUser] = useState({ username: '', email: '', password: '', role: 'STUDENT' });
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post("http://localhost:8081/api/users/register", user);
            alert("Registration Successful! Please Login.");
            navigate("/");
        } catch (error) {
            alert("Registration Failed.");
        }
    };

    return (
        <div className="container mt-5">
            <h2 className="text-center">Register New Account</h2>
            <form onSubmit={handleSubmit} className="card p-4 shadow mt-3 w-50 mx-auto">
                <div className="mb-2">
                    <label>Username</label>
                    <input className="form-control" onChange={(e) => setUser({...user, username: e.target.value})} required />
                </div>
                <div className="mb-2">
                    <label>Email</label>
                    <input className="form-control" type="email" onChange={(e) => setUser({...user, email: e.target.value})} required />
                </div>
                <div className="mb-2">
                    <label>Password</label>
                    <input className="form-control" type="password" onChange={(e) => setUser({...user, password: e.target.value})} required />
                </div>
                <div className="mb-3">
                    <label>Role</label>
                    <select className="form-control" onChange={(e) => setUser({...user, role: e.target.value})}>
                        <option value="STUDENT">Student</option>
                        <option value="ADMIN">Admin</option>
                    </select>
                </div>
                <button type="submit" className="btn btn-dark w-100">Register</button>
            </form>
        </div>
    );
};

export default RegisterPage;