package provider02.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import provider02.api.resources.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Integer> {

}
