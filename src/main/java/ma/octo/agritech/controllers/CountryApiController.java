package ma.octo.agritech.controllers;

import ma.octo.agritech.domains.City;
import ma.octo.agritech.domains.Country;
import ma.octo.agritech.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api", produces = {"application/json;charset=UTF-8"})
public class CountryApiController {

    @Autowired
    private CountryService countryService;

    @GetMapping(value = "/countries", produces = APPLICATION_JSON_VALUE)
    public List<Country> getAll() {

        return this.countryService.getAll();
    }

    //PreAuthorize("#oauth2.hasScope('api:read')")
    @GetMapping(value = "/countries/{country_ref}/cities", produces = APPLICATION_JSON_VALUE)
    public List<City> getCities(@PathVariable("country_ref") String countryRef) {

        return this.countryService.getCitiesByCountryRef(countryRef);
    }

}

