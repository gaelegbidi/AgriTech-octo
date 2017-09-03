package ma.octo.agritech.controllers;

import ma.octo.agritech.domains.Production;
import ma.octo.agritech.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="api")
public class ProductionApiController {
    private final ProductionRepository productionRepository;
    private final ExploitationRepository exploitationRepository;
    private final CompaignRepository compaignRepository;
    private final ProductRepository productRepository;
    private final FarmerRepository farmerRepository;

    @Autowired
    public ProductionApiController(ProductionRepository productionRepository, ExploitationRepository exploitationRepository, CompaignRepository compaignRepository, ProductRepository productRepository, FarmerRepository farmerRepository) {
        this.productionRepository = productionRepository;
        this.exploitationRepository = exploitationRepository;
        this.compaignRepository = compaignRepository;
        this.productRepository = productRepository;
        this.farmerRepository = farmerRepository;
    }

    @PostMapping(value = "/productions/store", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Production> Store(@RequestBody Production production, final Principal principal){

        production.setExploitation(this.exploitationRepository.findOneByRef(production.getExploitationRef()));
        production.setCompaign(this.compaignRepository.findOneByRef(production.getCompaignRef()));
        production.setProduct(this.productRepository.findOneByRef(production.getProductRef()));
        production.setFarmer(this.farmerRepository.findOneByUsername(principal.getName()));
        this.productionRepository.save(production);
        return new ResponseEntity<>(production,OK);
    }

    @GetMapping(value = "/productions/index", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Production>> index(){


        List<Production> prods = (List<Production>) this.productionRepository.findAll();
        return new ResponseEntity<>(prods,OK);
    }
}
