package provider02.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import provider02.api.resources.Authorities;

@Repository
public interface AuthorityRepository extends JpaRepository<Authorities, Integer> {
}
