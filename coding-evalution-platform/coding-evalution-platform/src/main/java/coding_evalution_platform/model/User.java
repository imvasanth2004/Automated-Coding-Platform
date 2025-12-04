package coding_evalution_platform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    // FIX: Changed 'long' to 'Long' (Allows null for new users)
    private Long id;
    
    @NotBlank(message = "Username is required")
    @Column(name="username", nullable=false, unique=true)
    private String username;
    
    @Email(message="Invalid Email format")
    @NotBlank(message="Email is required")
    @Column(name="email", nullable=false, unique=true)
    private String email;
    
    @NotBlank(message="Password is required")
    @Column(name="password", nullable=false)
    private String password;
    
    @NotBlank(message="Role is required")
    @Column(name="role", nullable=false)
    private String role;

    // --- CONSTRUCTORS ---

    public User() {
    }

    // FIX: Updated constructor to accept 'Long' instead of 'long'
    public User(Long id, String username, String email, String password, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // --- GETTERS AND SETTERS ---

    // FIX: Updated Getter to return 'Long'
    public Long getId() {
        return id;
    }

    // FIX: Updated Setter to accept 'Long'
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}