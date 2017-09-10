package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.Negociation;
import ma.octo.agritech.domains.Production;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NegociationRepository extends CrudRepository<Negociation, Long> {

    List<Negociation> findAllByProduction(Production production);
}
