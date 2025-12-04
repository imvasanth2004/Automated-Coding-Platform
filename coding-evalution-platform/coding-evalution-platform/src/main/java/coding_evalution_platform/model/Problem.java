package coding_evalution_platform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity; // <--- This is the key import!
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity // <--- This tells Spring "This is a Database Table"
@Table(name = "problems")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank(message = "Description is required")
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "Difficulty is required")
    @Column(name = "difficulty", nullable = false)
    private String difficulty;

    @NotBlank(message = "Tags is required")
    @Column(name = "tags", nullable = false)
    private String tags;

    @Column(name = "example_input", nullable = false)
    private String exampleInput;

    @Column(name = "example_output", nullable = false)
    private String exampleOutput;

    // --- CONSTRUCTORS ---

    public Problem() {
    }

    public Problem(Long id, String title, String description, String difficulty, String tags, String exampleInput, String exampleOutput) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.tags = tags;
        this.exampleInput = exampleInput;
        this.exampleOutput = exampleOutput;
    }

    // --- GETTERS AND SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getExampleInput() {
        return exampleInput;
    }

    public void setExampleInput(String exampleInput) {
        this.exampleInput = exampleInput;
    }

    public String getExampleOutput() {
        return exampleOutput;
    }

    public void setExampleOutput(String exampleOutput) {
        this.exampleOutput = exampleOutput;
    }
}