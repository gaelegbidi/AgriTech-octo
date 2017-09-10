package ma.octo.agritech.controllers;

import ma.octo.agritech.domains.Negociation;
import ma.octo.agritech.requests.StoreNegociationRequest;
import ma.octo.agritech.services.NegociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/negociations")
public class NegociationApiController {

    @Autowired
    private NegociationService negociationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Negociation store(@RequestBody StoreNegociationRequest storeNegociationRequest) {
        return this.negociationService.saveByStoreRequest(storeNegociationRequest);
    }

}
