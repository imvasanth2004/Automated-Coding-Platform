package coding_evalution_platform.service;

import coding_evalution_platform.model.Problem;
import coding_evalution_platform.Repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    // 1. Create a new problem (Admin only feature)
    public Problem createProblem(Problem problem) {
        return problemRepository.save(problem);
    }

    // 2. Get list of ALL problems (For the dashboard)
    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    // 3. Get a specific problem by ID (When a student clicks "Solve")
    public Problem getProblemById(Long id) {
        Optional<Problem> problem = problemRepository.findById(id);
        if (problem.isPresent()) {
            return problem.get();
        } else {
            throw new RuntimeException("Problem not found with ID: " + id);
        }
    }

    // 4. Get problems by difficulty (Filter feature)
    public List<Problem> getProblemsByDifficulty(String difficulty) {
        return problemRepository.findByDifficulty(difficulty);
    }
}