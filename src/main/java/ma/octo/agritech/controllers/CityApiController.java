package ma.octo.agritech.controllers;

import ma.octo.agritech.domains.City;
import ma.octo.agritech.domains.Country;
import ma.octo.agritech.domains.Village;
import ma.octo.agritech.repositories.CityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="api")
public class CityApiController {
    private CityRepository cityRepository;

    public CityApiController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping(value = "/cities/{city_ref}/villages", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<List<Village>> getVillage(@PathVariable("city_ref") String city_ref) {

        City city = this.cityRepository.findOneByRef(city_ref);
        return new ResponseEntity<>(city.getVillages(), OK);
    }

}
