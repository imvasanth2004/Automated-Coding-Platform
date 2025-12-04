package coding_evalution_platform.Repository;

import coding_evalution_platform.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    // We can add methods later like findByUserId(Long userId)
}