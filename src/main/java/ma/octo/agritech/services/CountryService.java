package ma.octo.agritech.services;

import ma.octo.agritech.domains.City;
import ma.octo.agritech.domains.Country;
import ma.octo.agritech.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAll() {

        List<Country> countries = new ArrayList<>();

        this.countryRepository.findAll().forEach(countries::add);

        return countries;
    }

    public List<City> getCitiesByCountryRef(String countryRef) {

        Country country = this.countryRepository.findOneByRef(countryRef);

        return country.getCities();
    }
}
