package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.Negociation;
import ma.octo.agritech.domains.Production;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductionRepository extends CrudRepository<Production, Long> {

    @Query("select p.negociations from Production p  where p.id = ?1 ")
    List<Negociation> findAllNegociationsByProductionId(Long productionId);
}
