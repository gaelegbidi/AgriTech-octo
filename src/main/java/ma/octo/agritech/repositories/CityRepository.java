package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    City findOneByRef(String city_ref);
}

