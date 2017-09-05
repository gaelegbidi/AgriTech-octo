package ma.octo.agritech.controllers;

import ma.octo.agritech.repositories.VillageRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api")
public class VillageApiController {
    private VillageRepository villageRepository;

    public VillageApiController(VillageRepository villageRepository) {
        this.villageRepository = villageRepository;
    }
}
