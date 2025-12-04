package coding_evalution_platform.Repository;

import coding_evalution_platform.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {
    // Custom query to find problems by difficulty (e.g., "Find all Easy problems")
    List<Problem> findByDifficulty(String difficulty);
}