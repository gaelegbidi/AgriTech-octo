package ma.octo.agritech.services;

import ma.octo.agritech.Requests.StoreUserRequest;
import ma.octo.agritech.config.IAuthenticationFacade;
import ma.octo.agritech.domains.*;
import ma.octo.agritech.repositories.NegociationRepository;
import ma.octo.agritech.repositories.ProductionRepository;
import ma.octo.agritech.repositories.RoleRepository;
import ma.octo.agritech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
   private RoleRepository roleRepository;
    @Autowired
    private IAuthenticationFacade authenticationFacade;
    @Autowired
    private ProductionRepository productionRepository;
    @Autowired
    private NegociationRepository negociationRepository;


    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        this.userRepository.findAll().forEach(users::add);
        return users;
    }

    public List<Production> getAllAuthProductions() {
        return this.userRepository.findProductionsByUsername(this.getAuthUsername());
    }

    public String getAuthUsername(){
        Authentication authentication = this.authenticationFacade.getAuthentication();
        return authentication.getName();
    }
    public User getAuth(){
        return this.getByUsername(this.getAuthUsername());
    }
    /**
     * Get Users statistic
     *
     * @return UserStats
     */
    public UserStats getUserStats() {
        List<User> users = (List<User>) this.userRepository.findAll();
        List<User> usersAcheteur = this.userRepository.findByRoles("ROLE_ACHETEUR");
        List<User> usersPartenaire = this.userRepository.findByRoles("ROLE_PARTENAIRE");
        List<User> usersONG = this.userRepository.findByRoles("ROLE_ONG");
        List<User> usersPublic = this.userRepository.findByRoles("ROLE_PUBLIC");
        return new UserStats(users.size(), usersAcheteur.size(), usersPartenaire.size(), usersPublic.size(), usersONG.size());
    }

    public User getByUsername(String username) {
        Optional<User> userOptional = this.userRepository.findOneByUsername(username);
        userOptional.orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));
        return userOptional.map(User::new).get();
    }

    public User saveByStoreRequest(StoreUserRequest storeUserRequest) {
        User user = new User(storeUserRequest);
//        Role role = this.roleRepository.findOneByRef(storeUserRequest.getRoleRef());
        List<Role> roles = new ArrayList<>();
        String[] rolesTab = storeUserRequest.getRoleRef().split("|");
        for (int i = 0; i < rolesTab.length; i++) {
            roles.add(this.roleRepository.findOneByRef(rolesTab[i]));
            user.setRoles(roles);
            this.userRepository.save(user);
            return user;
        }
        this.userRepository.save(user);
        return  user;
    }
}
