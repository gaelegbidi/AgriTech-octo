package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.Role;
import org.springframework.data.repository.CrudRepository;


public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findOneByRef(String ref);
}
