package coding_evalution_platform.service;

import coding_evalution_platform.model.Problem;
import coding_evalution_platform.model.Submission;
import coding_evalution_platform.Repository.ProblemRepository;
import coding_evalution_platform.Repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private ProblemRepository problemRepository;

    public Submission submitCode(Submission submission) {
        // 1. Fetch the Problem to get test cases (Example Input/Output)
        Problem problem = problemRepository.findById(submission.getProblemId())
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        // 2. Execute the Code using ProcessBuilder
        String actualOutput = runJavaCode(submission.getCode(), problem.getExampleInput());
        
        // 3. Compare Output
        submission.setOutput(actualOutput);
        
        // Trim removes extra spaces/newlines for accurate comparison
        if (actualOutput.trim().equals(problem.getExampleOutput().trim())) {
            submission.setVerdict("PASSED");
        } else {
            submission.setVerdict("FAILED");
        }

        // 4. Save to Database
        return submissionRepository.save(submission);
    }

    // --- THE CORE RESUME LOGIC: PROCESS BUILDER ---
    private String runJavaCode(String code, String input) {
        try {
            // A. Create a temporary file: Solution.java
            Path tempDir = Files.createTempDirectory("coding_platform");
            File sourceFile = new File(tempDir.toFile(), "Solution.java");
            Files.write(sourceFile.toPath(), code.getBytes());

            // B. Compile: javac Solution.java
            ProcessBuilder compileProcess = new ProcessBuilder("javac", sourceFile.getAbsolutePath());
            compileProcess.directory(tempDir.toFile());
            Process javac = compileProcess.start();
            javac.waitFor();

            if (javac.exitValue() != 0) {
                return "COMPILATION ERROR: Check your syntax.";
            }

            // C. Run: java Solution
            ProcessBuilder runProcess = new ProcessBuilder("java", "-cp", tempDir.toString(), "Solution");
            Process java = runProcess.start();

            // D. Feed Input to the running process
            try (OutputStream os = java.getOutputStream()) {
                os.write(input.getBytes());
                os.flush();
            }

            // E. Capture Output
            // Wait max 5 seconds (to prevent infinite loops)
            if (!java.waitFor(5, TimeUnit.SECONDS)) {
                java.destroy();
                return "TIME LIMIT EXCEEDED";
            }

            // Read the output
            BufferedReader reader = new BufferedReader(new InputStreamReader(java.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            return output.toString().trim();

        } catch (Exception e) {
            return "EXECUTION ERROR: " + e.getMessage();
        }
    }
}