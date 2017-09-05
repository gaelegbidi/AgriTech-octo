package ma.octo.agritech.controllers;

import ma.octo.agritech.domains.Village;
import ma.octo.agritech.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api")
public class CityApiController {
    @Autowired
    private CityService cityService;

    @GetMapping(value = "/cities/{city_ref}/villages", produces = APPLICATION_JSON_VALUE)
    public List<Village> getVillage(@PathVariable("city_ref") String cityRef) {

        return this.cityService.getVillagesByCountryRef(cityRef);
    }

}
