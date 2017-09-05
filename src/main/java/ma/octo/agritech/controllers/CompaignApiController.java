package ma.octo.agritech.controllers;

import ma.octo.agritech.repositories.CompaignRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api")
public class CompaignApiController {

    private final CompaignRepository compaignRepository;

    public CompaignApiController(CompaignRepository compaignRepository) {
        this.compaignRepository = compaignRepository;
    }
}
