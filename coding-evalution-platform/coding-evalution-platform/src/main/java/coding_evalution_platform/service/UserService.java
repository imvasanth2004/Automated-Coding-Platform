package coding_evalution_platform.service;

import coding_evalution_platform.model.User;
import coding_evalution_platform.Repository.UserRepository; // Make sure package name matches your folder (repository vs Repository)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method to register a new user
    public User registerUser(User user) {
        // 1. Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already taken!");
        }
        
        // 2. Check if username already exists
        // FIX: Changed getUser_name() to getUsername() to match the User class
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already taken!");
        }

        // 3. Save the user to the database
        return userRepository.save(user);
    }

    // Method to login
    public User loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}