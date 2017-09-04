package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.Production;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductionRepository extends CrudRepository<Production, Long> {

}
