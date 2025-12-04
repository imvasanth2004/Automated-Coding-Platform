import React from 'react';
import { Link } from 'react-router-dom';

const MyNavbar = () => {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark px-4">
            <Link className="navbar-brand fw-bold text-warning" to="/">
                &lt;Code/&gt; Platform
            </Link>
            
            <div className="collapse navbar-collapse">
                <ul className="navbar-nav ms-auto">
                    <li className="nav-item">
                        <Link className="nav-link text-white" to="/">Home</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link text-white" to="/add-problem">Admin: Add Problem</Link>
                    </li>
                </ul>
            </div>
        </nav>
    );
};

export default MyNavbar;