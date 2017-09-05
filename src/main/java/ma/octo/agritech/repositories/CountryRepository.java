package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {

    Country findOneByRef(String ref);
}
