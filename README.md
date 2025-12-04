Automated Coding Evaluation Platform

A full-stack coding assessment platform similar to HackerRank or LeetCode. It allows users to view coding challenges, write Java code in a real-time editor, and receive instant feedback via a secure backend compilation engine.

Key Features

Real-time Code Execution: Compiles and runs user-submitted Java code dynamically using the ProcessBuilder API.

Secure Authentication: Full JWT (JSON Web Token) implementation with role-based access control (Admin vs. Student).

Professional Editor: Integrated Monaco Editor (the engine powering VS Code) for a premium coding experience.

Admin Dashboard: Admins can securely add new problems, test cases, and manage content.

Full Stack Architecture:Completely decoupled React Frontend talking to a Spring Boot Backend via REST APIs.

Tech Stack

Backend

Java 21 \& Spring Boot 3+ (Core Framework)

Spring Security \& JWT (Authentication \& Authorization)

Hibernate \& JPA (Database ORM)

H2 Database  (In-Memory Database for rapid testing)

Java ProcessBuilder  (For compiling external code)

Frontend

React.js (User Interface)

Bootstrap 5 (Responsive Styling)

Axios (API Communication)

React Router (Navigation)

Monaco Editor (Code Editor Component)


 Project Architecture



1.User Submission:User types code in React and clicks "Run".

2.API Call: React sends the code + Problem ID to Spring Boot securely (with JWT).

3.Compilation Engine:

&nbsp;   The Backend writes the code to a temporary `Solution.java` file.

&nbsp;   It invokes the system `javac` compiler.

&nbsp;   It runs the compiled class against hidden test cases.

4.*Verdict: The output is compared ("PASSED" or "FAILED") and sent back to the frontend.

 How to Run Locally

 Prerequisites

 Java JDK 17 or 21

 Node.js (v18+)

 Maven

1. Setup Backend (Spring Boot)

1  Navigate to the `coding-evalution-platform` folder.

2 Open in Eclipse or IntelliJ.

3  Run `CodingEvaluationPlatformApplication.java`.

4  Server starts on: `http://localhost:8081`



2. Setup Frontend (React)

1 Navigate to the `coding-platform-frontend` folder.

2 Install dependencies:

&nbsp;   ```bash

&nbsp;   npm install

&nbsp;   ```

3  Start the server:

&nbsp;   ```bash

&nbsp;   npm start

&nbsp;   ```

4  App opens at: `http://localhost:3000`

Default Credentials


Since the project uses an In-Memory Database (H2), users are reset on restart.

Register a new user or use the flows below:

Admin Role: Can add problems (Route: `/add-problem`).

Student Role: Can solve problems (Route: `/solve/{id}`).


Built by Vasanth sai Kilaparthi

