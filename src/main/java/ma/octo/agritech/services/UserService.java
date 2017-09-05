package ma.octo.agritech.services;

import ma.octo.agritech.config.IAuthenticationFacade;
import ma.octo.agritech.domains.CustomUserDetails;
import ma.octo.agritech.domains.Production;
import ma.octo.agritech.domains.User;
import ma.octo.agritech.domains.UserStats;
import ma.octo.agritech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;


    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        this.userRepository.findAll().forEach(users::add);
        return users;
    }

    public List<Production> getAllAuthProductions() {

        Authentication authentication = this.authenticationFacade.getAuthentication();
        Optional<User> userOptional = this.userRepository.findOneByUsername(authentication.getName());
        userOptional.orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));
        User user = userOptional.map(CustomUserDetails::new).get();

        return user.getProductions();
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
}
