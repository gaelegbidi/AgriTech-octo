package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.Village;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VillageRepository extends CrudRepository<Village, Long> {
    Village findOneByRef(String Ref);
}
