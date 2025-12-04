import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import ProblemService from '../services/ProblemService';

const HomePage = () => {
    const [problems, setProblems] = useState([]);

    // This runs automatically when the page loads
    useEffect(() => {
        ProblemService.getAllProblems()
            .then((response) => {
                setProblems(response.data); // Store data from Java in React
                console.log("Data received:", response.data);
            })
            .catch((error) => {
                console.error("Error fetching problems:", error);
            });
    }, []);

    return (
        <div className="container mt-5">
            <div className="text-center mb-5">
                <h1 className="display-4 fw-bold text-dark">Problem Dashboard</h1>
                <p className="lead text-muted">Sharpen your coding skills with our AI-powered challenges.</p>
            </div>

            <div className="row">
                {problems.length === 0 ? (
                    <div className="col-12 text-center">
                        <div className="alert alert-warning">No problems found. Did you restart the backend?</div>
                    </div>
                ) : (
                    problems.map((problem) => (
                        <div className="col-md-6 mb-4" key={problem.id}>
                            <div className="card shadow-sm border-0 h-100 hover-effect">
                                <div className="card-body">
                                    <h5 className="card-title fw-bold">{problem.title}</h5>
                                    
                                    <span className={`badge mb-3 ${
                                        problem.difficulty === 'Easy' ? 'bg-success' : 
                                        problem.difficulty === 'Medium' ? 'bg-warning' : 'bg-danger'
                                    }`}>
                                        {problem.difficulty}
                                    </span>

                                    <p className="card-text text-muted">
                                        {problem.description.length > 100 
                                            ? problem.description.substring(0, 100) + "..." 
                                            : problem.description}
                                    </p>

                                    <Link to={`/solve/${problem.id}`} className="btn btn-outline-primary w-100">
                                        Solve Challenge &rarr;
                                    </Link>
                                </div>
                            </div>
                        </div>
                    ))
                )}
            </div>
        </div>
    );
};

export default HomePage;