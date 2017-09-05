package ma.octo.agritech.controllers;

import ma.octo.agritech.domains.City;
import ma.octo.agritech.domains.Country;
import ma.octo.agritech.repositories.CityRepository;
import ma.octo.agritech.repositories.CountryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api",produces = {"application/json;charset=UTF-8"})
public class CountryApiController {
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    public CountryApiController(CountryRepository repository, CityRepository cityRepository) {
        this.countryRepository = repository;
        this.cityRepository = cityRepository;
    }

    //PreAuthorize("#oauth2.hasScope('api:read')")
    @GetMapping(value = "/countries/{country_ref}/cities", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<List<City>> getCities(@PathVariable("country_ref") String country_ref) {

        Country country = this.countryRepository.findOneByRef(country_ref);
        return new ResponseEntity<>(country.getCities(), OK);
    }
}

