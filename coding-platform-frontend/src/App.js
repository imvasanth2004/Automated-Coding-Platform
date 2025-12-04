import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MyNavbar from './components/MyNavbar';
import HomePage from './pages/HomePage';
import ProblemPage from './pages/ProblemPage'; // <--- Check this import!
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import AddProblemPage from './pages/AddProblemPage';

function App() {
  return (
    <Router>
      <MyNavbar />
      <div className="container-fluid p-0">
        <Routes>
          <Route path="/" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/dashboard" element={<HomePage />} />
          
          {/* CRITICAL: Ensure this line says ProblemPage, NOT AddProblemPage */}
          <Route path="/solve/:id" element={<ProblemPage />} />
          
          <Route path="/add-problem" element={<AddProblemPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;