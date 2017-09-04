package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CityRepository extends CrudRepository<City, Long> {
    List<City> findCitiesByCountry(String country);

    City findOneByRef(String city_ref);
}

