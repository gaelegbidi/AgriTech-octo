package ma.octo.agritech.services;

import ma.octo.agritech.domains.City;
import ma.octo.agritech.domains.Village;
import ma.octo.agritech.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<Village> getVillagesByCountryRef(String cityRef) {

        City city = this.cityRepository.findOneByRef(cityRef);
        return city.getVillages();
    }
}
