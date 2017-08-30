package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.Farmer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FarmerRepository extends CrudRepository<Farmer, Long> {
    Farmer findOneByUsername(String username);
}
