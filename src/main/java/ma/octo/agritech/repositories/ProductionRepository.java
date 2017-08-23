package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.Production;
import ma.octo.agritech.domains.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductionRepository extends CrudRepository<Production, Long>{

    //List<Production> findByCompaign(String campaign);
}
