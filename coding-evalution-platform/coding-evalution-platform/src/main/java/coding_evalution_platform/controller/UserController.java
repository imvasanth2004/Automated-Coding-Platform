package coding_evalution_platform.controller;

import coding_evalution_platform.model.User;
import coding_evalution_platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Tells Spring this class handles HTTP Requests
@RequestMapping("/api/users") // Base URL: http://localhost:8080/api/users
@CrossOrigin(origins = "http://localhost:3000") // Allows React to talk to this backend
public class UserController {

    @Autowired
    private UserService userService;

    // Register API: POST http://localhost:8080/api/users/register
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user);
            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @Autowired
    private coding_evalution_platform.security.JwtUtil jwtUtil; // Import this

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            User loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());
            
            // GENERATE JWT TOKEN
            String token = jwtUtil.generateToken(loggedInUser.getUsername());
            
            // Return Token + User Role
            return ResponseEntity.ok(new LoginResponse(token, loggedInUser.getRole(), loggedInUser.getUsername()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // Tiny helper class for the response
    static class LoginResponse {
        public String token;
        public String role;
        public String username;
        
        public LoginResponse(String token, String role, String username) {
            this.token = token;
            this.role = role;
            this.username = username;
        }
    }
}