package ma.octo.agritech.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import ma.octo.agritech.domains.User;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {

    User findOneByUsername(String username);
    List<User> findByRoles(String roles);
}
