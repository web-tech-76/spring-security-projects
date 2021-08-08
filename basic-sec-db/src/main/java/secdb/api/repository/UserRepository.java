package secdb.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import secdb.api.resource.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String username);

}
