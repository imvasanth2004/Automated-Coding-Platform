import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const AddProblemPage = () => {
    const [problem, setProblem] = useState({
        title: '', description: '', difficulty: 'Easy', tags: '', exampleInput: '', exampleOutput: ''
    });
    const navigate = useNavigate();

    const handleChange = (e) => {
        setProblem({...problem, [e.target.name]: e.target.value});
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem("token"); // Get Token
        
        try {
            // Send Request with Token
            await axios.post("http://localhost:8081/api/problems", problem, {
                headers: { Authorization: `Bearer ${token}` }
            });
            alert("Problem Added Successfully!");
            navigate("/dashboard");
        } catch (error) {
            alert("Failed to add problem. Are you an Admin?");
        }
    };

    return (
        <div className="container mt-5">
            <h2 className="text-center mb-4">Admin: Add New Challenge</h2>
            <form onSubmit={handleSubmit} className="card p-4 shadow">
                <div className="mb-3">
                    <label>Title</label>
                    <input name="title" className="form-control" onChange={handleChange} required />
                </div>
                <div className="mb-3">
                    <label>Description</label>
                    <textarea name="description" className="form-control" rows="3" onChange={handleChange} required />
                </div>
                <div className="row">
                    <div className="col-md-6 mb-3">
                        <label>Difficulty</label>
                        <select name="difficulty" className="form-control" onChange={handleChange}>
                            <option>Easy</option><option>Medium</option><option>Hard</option>
                        </select>
                    </div>
                    <div className="col-md-6 mb-3">
                        <label>Tags</label>
                        <input name="tags" className="form-control" placeholder="e.g. Arrays, Java" onChange={handleChange} required />
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-6 mb-3">
                        <label>Example Input</label>
                        <textarea name="exampleInput" className="form-control" onChange={handleChange} required />
                    </div>
                    <div className="col-md-6 mb-3">
                        <label>Example Output</label>
                        <textarea name="exampleOutput" className="form-control" onChange={handleChange} required />
                    </div>
                </div>
                <button type="submit" className="btn btn-success w-100">Add Problem</button>
            </form>
        </div>
    );
};

export default AddProblemPage;