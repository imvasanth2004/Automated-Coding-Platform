package coding_evalution_platform.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coding_evalution_platform.model.User;

@Repository

public interface UserRepository extends JpaRepository<User,Long> {
	// This method automatically writes the SQL query to find a user by email
    Optional<User> findByEmail(String email);
    
    // This finds by username (useful for login)
    Optional<User> findByUsername(String username);

}
