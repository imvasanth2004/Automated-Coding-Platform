import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import ProblemService from '../services/ProblemService';
import Editor from '@monaco-editor/react';

const ProblemPage = () => {
    const { id } = useParams();
    const [problem, setProblem] = useState(null);
    const [code, setCode] = useState("// Write your code here\n");
    const [verdict, setVerdict] = useState(null);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        ProblemService.getProblemById(id).then((response) => {
            setProblem(response.data);
            // Pre-fill with a generic template
            setCode(`import java.util.Scanner;\n\npublic class Solution {\n    public static void main(String[] args) {\n        Scanner s = new Scanner(System.in);\n        // Write logic here\n        \n    }\n}`);
        });
    }, [id]);

    const handleSubmit = () => {
        setLoading(true);
        setVerdict(null);

        const submission = {
            userId: 1, // We can fix this later to use real user ID
            problemId: id,
            language: "Java",
            code: code
        };

        ProblemService.submitCode(submission)
            .then((response) => {
                setVerdict(response.data);
                setLoading(false);
            })
            .catch((error) => {
                console.error("Error:", error);
                setLoading(false);
            });
    };

    if (!problem) return <div className="text-center mt-5">Loading Problem...</div>;

    return (
        <div className="container-fluid mt-4">
            <div className="row">
                {/* Problem Description */}
                <div className="col-md-5 px-4">
                    <div className="card shadow-sm border-0">
                        <div className="card-body">
                            <h3>{problem.title}</h3>
                            <span className="badge bg-success mb-3">{problem.difficulty}</span>
                            <p>{problem.description}</p>
                            <div className="bg-light p-2 mb-2"><strong>Input:</strong> {problem.exampleInput}</div>
                            <div className="bg-light p-2"><strong>Output:</strong> {problem.exampleOutput}</div>
                        </div>
                    </div>
                    {/* Results Area */}
                    {verdict && (
                        <div className={`alert mt-3 ${verdict.verdict === 'PASSED' ? 'alert-success' : 'alert-danger'}`}>
                            <strong>Result: {verdict.verdict}</strong>
                            <pre>{verdict.output}</pre>
                        </div>
                    )}
                </div>

                {/* Code Editor */}
                <div className="col-md-7">
                    <div className="card shadow-lg">
                        <div className="card-header bg-dark text-white d-flex justify-content-between">
                            <span>Java Solution</span>
                            <button className="btn btn-success btn-sm" onClick={handleSubmit} disabled={loading}>
                                {loading ? "Running..." : "Run Code >>"}
                            </button>
                        </div>
                        <Editor
                            height="80vh"
                            defaultLanguage="java"
                            theme="vs-dark"
                            value={code}
                            onChange={(val) => setCode(val)}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ProblemPage;