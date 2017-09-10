package ma.octo.agritech.services;

import ma.octo.agritech.config.IAuthenticationFacade;
import ma.octo.agritech.domains.Negociation;
import ma.octo.agritech.repositories.NegociationRepository;
import ma.octo.agritech.repositories.ProductionRepository;
import ma.octo.agritech.requests.StoreNegociationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class NegociationService {

    @Autowired
    private NegociationRepository negociationRepository;
    @Autowired
    private ProductionService productionService;
    @Autowired
    private ProductionRepository productionRepository;
    @Autowired
    private IAuthenticationFacade authenticationFacade;
    @Autowired
    private UserService userService;

    public Negociation saveByStoreRequest(StoreNegociationRequest storeNegociationRequest) {
        Negociation negociation = new Negociation();

        negociation.setPrice(storeNegociationRequest.getPrice());
        negociation.setProduction(this.productionRepository.findOne(storeNegociationRequest.getProductionId()));
        Authentication authentication = this.authenticationFacade.getAuthentication();

        if (authentication != null) {
            negociation.setUser(this.userService.getByUsername(authentication.getName()));
        }
        this.negociationRepository.save(negociation);
        return negociation;

    }


}
