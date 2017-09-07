package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.Production;
import ma.octo.agritech.domains.Role;
import ma.octo.agritech.domains.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findOneByUsername(String username);

    @Query("SELECT r FROM User u JOIN u.roles r WHERE u.username = ?1 ")
    List<Role> findRolesByUsername(String username);

    @Query("SELECT p FROM User u JOIN u.productions p WHERE u.username = ?1 ")
    List<Production> findProductionsByUsername(String username);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles r WHERE r.ref = ?1 ")
    List<User> findByRoles(String roles);
}
