package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.Village;
import org.springframework.data.repository.CrudRepository;

public interface VillageRepository extends CrudRepository<Village, Long> {

    Village findOneByRef(String Ref);

    Village findOneByName(String villageName);
}
