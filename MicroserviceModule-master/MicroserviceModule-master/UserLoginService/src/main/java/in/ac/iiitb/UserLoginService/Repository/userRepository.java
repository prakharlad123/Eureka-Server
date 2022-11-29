package in.ac.iiitb.UserLoginService.Repository;

import in.ac.iiitb.UserLoginService.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
