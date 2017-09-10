package ma.octo.agritech.controllers;

import ma.octo.agritech.domains.Negociation;
import ma.octo.agritech.domains.Production;
import ma.octo.agritech.requests.StoreProductionRequest;
import ma.octo.agritech.services.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/productions")
public class ProductionApiController {

    @Autowired
    private ProductionService productionService;


    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Production> index() {
        return this.productionService.getAll();
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Production store(@RequestBody StoreProductionRequest storeProductionRequest) {
        return this.productionService.saveByStoreRequest(storeProductionRequest);
    }

    @GetMapping(value = "/{id}/negociations", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Negociation> getNegociationByProduction(@PathVariable("id") Long productionId) {
        return this.productionService.getNegociationByProductionId(productionId);
    }
}
