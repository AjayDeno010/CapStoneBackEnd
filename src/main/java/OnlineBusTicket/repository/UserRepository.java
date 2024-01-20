package OnlineBusTicket.repository;

import OnlineBusTicket.entity.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);
//  Optional<User> findByEmailOrUserName(String email, String userName);
}
