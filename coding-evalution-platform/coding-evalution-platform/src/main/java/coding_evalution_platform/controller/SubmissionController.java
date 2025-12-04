package coding_evalution_platform.controller;

import coding_evalution_platform.model.Submission;
import coding_evalution_platform.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions") // Base URL: http://localhost:8081/api/submissions
@CrossOrigin(origins = "*")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    // POST http://localhost:8081/api/submissions
    @PostMapping
    public ResponseEntity<?> submitCode(@RequestBody Submission submission) {
        try {
            Submission result = submissionService.submitCode(submission);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}