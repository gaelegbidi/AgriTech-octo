package ma.octo.agritech.services;

import ma.octo.agritech.repositories.ProductionRepository;
import ma.octo.agritech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ProductionService {
    @Autowired
    private ProductionRepository prodRepository;


}
