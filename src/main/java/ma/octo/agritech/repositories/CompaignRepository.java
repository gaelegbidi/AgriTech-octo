package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.Compaign;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CompaignRepository extends CrudRepository<Compaign, Long>{
    Compaign findOneByRef(String ref);
}
