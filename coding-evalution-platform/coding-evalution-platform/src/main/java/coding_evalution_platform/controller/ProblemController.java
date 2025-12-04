package coding_evalution_platform.controller;

import coding_evalution_platform.model.Problem;
import coding_evalution_platform.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problems") // Base URL: http://localhost:8081/api/problems
@CrossOrigin(origins = "*")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    // 1. Create a Problem (POST http://localhost:8081/api/problems)
    @PostMapping
    public ResponseEntity<Problem> createProblem(@RequestBody Problem problem) {
        Problem newProblem = problemService.createProblem(problem);
        return ResponseEntity.ok(newProblem);
    }

    // 2. Get All Problems (GET http://localhost:8081/api/problems)
    @GetMapping
    public List<Problem> getAllProblems() {
        return problemService.getAllProblems();
    }

    // 3. Get Single Problem (GET http://localhost:8081/api/problems/{id})
    @GetMapping("/{id}")
    public ResponseEntity<?> getProblemById(@PathVariable Long id) {
        try {
            Problem problem = problemService.getProblemById(id);
            return ResponseEntity.ok(problem);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 4. Filter by Difficulty (GET http://localhost:8081/api/problems/difficulty/{level})
    @GetMapping("/difficulty/{level}")
    public List<Problem> getProblemsByDifficulty(@PathVariable String level) {
        return problemService.getProblemsByDifficulty(level);
    }
}