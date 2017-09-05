package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.Compaign;
import org.springframework.data.repository.CrudRepository;

public interface CompaignRepository extends CrudRepository<Compaign, Long> {
    Compaign findOneByRef(String ref);
}
