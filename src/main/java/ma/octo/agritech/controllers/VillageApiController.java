package ma.octo.agritech.controllers;

import ma.octo.agritech.services.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api")
public class VillageApiController {
    @Autowired
    private VillageService villageService;
}
