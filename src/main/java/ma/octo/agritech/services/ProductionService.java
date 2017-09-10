package ma.octo.agritech.services;

import ma.octo.agritech.config.IAuthenticationFacade;
import ma.octo.agritech.domains.Negociation;
import ma.octo.agritech.domains.Production;
import ma.octo.agritech.repositories.*;
import ma.octo.agritech.requests.StoreProductionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductionService {
    @Autowired
    private ProductionRepository productionRepository;
    @Autowired
    private CompaignRepository compaignRepository;
    @Autowired
    private ExploitationRepository exploitationRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private IAuthenticationFacade authenticationFacade;
    @Autowired
    private NegociationRepository negociationRepository;

    public List<Production> getAll() {
        List<Production> productions = new ArrayList<>();
        this.productionRepository.findAll().forEach(productions::add);
        return productions;
    }

    public Production saveByStoreRequest(StoreProductionRequest storeProductionRequest) {

        Production production = new Production();
        production.setCompaign(this.compaignRepository.findOneByRef(storeProductionRequest.getCompaignRef()));
        production.setExploitation(this.exploitationRepository.findOneByRef(storeProductionRequest.getExploitationRef()));
        production.setProduct(this.productRepository.findOneByRef(storeProductionRequest.getProductRef()));
        production.setQuantity(storeProductionRequest.getQuantity());

        Authentication authentication = this.authenticationFacade.getAuthentication();
        if (authentication != null) {
            production.setUser(this.userService.getByUsername(authentication.getName()));
        }
        this.productionRepository.save(production);
        return production;

    }

    public List<Negociation> getNegociationByProductionId(Long productionId) {
//          return this.productionRepository.findAllNegociationsByProductionId(productionId);
        return this.negociationRepository.findAllByProduction(this.productionRepository.findOne(productionId));
    }
}
