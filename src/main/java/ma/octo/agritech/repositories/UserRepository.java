package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {

    User findOneByUsername(String username);

    List<User> findByRoles(String roles);
}
