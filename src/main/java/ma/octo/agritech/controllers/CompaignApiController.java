package ma.octo.agritech.controllers;

import ma.octo.agritech.domains.Compaign;
import ma.octo.agritech.services.CompaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/compaigns")
public class CompaignApiController {
    @Autowired
    private CompaignService compaignService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Compaign> getAll() {
        return this.compaignService.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Compaign store(@RequestBody Compaign compaign) {
        return this.compaignService.save(compaign);
    }
}
