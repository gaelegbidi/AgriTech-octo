package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.Production;
import ma.octo.agritech.domains.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductionRepository extends CrudRepository<Production, Long>{

}
