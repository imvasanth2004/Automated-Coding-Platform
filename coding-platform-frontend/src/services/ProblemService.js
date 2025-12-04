import axios from 'axios';

const PROBLEM_API_URL = "http://localhost:8081/api/problems";
const SUBMISSION_API_URL = "http://localhost:8081/api/submissions";

class ProblemService {
    
    // Helper function: Gets the token from browser storage
    getAuthHeader() {
        const token = localStorage.getItem("token");
        if (!token) return {}; // Return empty if no token (user not logged in)
        return { headers: { Authorization: `Bearer ${token}` } };
    }

    // 1. Get all problems (Now Secured!)
    getAllProblems() {
        return axios.get(PROBLEM_API_URL, this.getAuthHeader());
    }

    // 2. Save a new problem (Admin)
    createProblem(problem) {
        return axios.post(PROBLEM_API_URL, problem, this.getAuthHeader());
    }

    // 3. Get a single problem by ID
    getProblemById(id) {
        return axios.get(PROBLEM_API_URL + "/" + id, this.getAuthHeader());
    }

    // 4. Submit Code
    submitCode(submission) {
        return axios.post(SUBMISSION_API_URL, submission, this.getAuthHeader());
    }
}

export default new ProblemService();