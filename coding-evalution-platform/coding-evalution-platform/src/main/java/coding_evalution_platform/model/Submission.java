package coding_evalution_platform.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", columnDefinition = "TEXT", nullable = false)
    private String code;

    @Column(name = "language", nullable = false)
    private String language; // e.g., "Java", "Python"

    @Column(name = "verdict")
    private String verdict; // "PASSED", "FAILED", "COMPILATION_ERROR"

    // IMPORTANT: "TEXT" allows this to store large error messages or long outputs
    @Column(name = "output", columnDefinition = "TEXT")
    private String output; 

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    // Relationship: A submission MUST belong to a user
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // Relationship: A submission MUST belong to a problem
    @Column(name = "problem_id", nullable = false)
    private Long problemId;

    // --- CONSTRUCTORS ---

    public Submission() {
        this.submittedAt = LocalDateTime.now();
    }

    public Submission(String code, String language, Long userId, Long problemId) {
        this.code = code;
        this.language = language;
        this.userId = userId;
        this.problemId = problemId;
        this.submittedAt = LocalDateTime.now();
    }

    // --- GETTERS AND SETTERS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getVerdict() { return verdict; }
    public void setVerdict(String verdict) { this.verdict = verdict; }

    public String getOutput() { return output; }
    public void setOutput(String output) { this.output = output; }

    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getProblemId() { return problemId; }
    public void setProblemId(Long problemId) { this.problemId = problemId; }
}