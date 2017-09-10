package ma.octo.agritech.services;

import ma.octo.agritech.config.IAuthenticationFacade;
import ma.octo.agritech.domains.Production;
import ma.octo.agritech.domains.Role;
import ma.octo.agritech.domains.User;
import ma.octo.agritech.domains.UserStats;
import ma.octo.agritech.repositories.NegociationRepository;
import ma.octo.agritech.repositories.ProductionRepository;
import ma.octo.agritech.repositories.RoleRepository;
import ma.octo.agritech.repositories.UserRepository;
import ma.octo.agritech.requests.EditProfileUserRequest;
import ma.octo.agritech.requests.StoreUserRequest;
import ma.octo.agritech.requests.UpdateUserRequest;
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

    public String getAuthUsername() {
        Authentication authentication = this.authenticationFacade.getAuthentication();
        return authentication.getName();
    }

    public User getAuth() {
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
        Role role = this.roleRepository.findOneByRef(storeUserRequest.getRoleRef());
        user.setRoles(this.roleStringToList(storeUserRequest.getRoleRef()));
        this.userRepository.save(user);
        return user;
    }

    private List<Role> roleStringToList(String roleRef) {
        List<Role> roles = new ArrayList<>();
        String[] rolesTab = roleRef.split("\\|");
        for (String aRolesTab : rolesTab) {
            roles.add(this.roleRepository.findOneByRef(aRolesTab));

        }
       return roles;
    }

    public User getById(Long id) {
        return this.userRepository.findOne(id);
    }

    public void deleteById(Long id) {
        User user = this.getAuth();
        if (user != null && user.hasRole("ROLE_ADMIN")) {
            this.userRepository.delete(id);
        }
    }

    public User saveByUpdateRequest(Long id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findOne(id);
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setEmail(updateUserRequest.getPhone());
        user.setFunction(updateUserRequest.getFunction());
        user.setAddress(updateUserRequest.getAddress());
        user.setCity(updateUserRequest.getCity());
        user.setCountry(updateUserRequest.getCountry());
        user.setImage(updateUserRequest.getImage());
        user.setSociety(updateUserRequest.getSociety());
        user.setRoles(this.roleStringToList(updateUserRequest.getRoleRef()));
        this.userRepository.save(user);
        return  user;
    }

    public User saveByEditProfileRequest(EditProfileUserRequest editProfileUserRequest) {

        User user = this.getAuth();
        user.setFirstName(editProfileUserRequest.getFirstName());
        user.setLastName(editProfileUserRequest.getLastName());
        user.setEmail(editProfileUserRequest.getPhone());
        user.setFunction(editProfileUserRequest.getFunction());
        user.setAddress(editProfileUserRequest.getAddress());
        user.setCity(editProfileUserRequest.getCity());
        user.setCountry(editProfileUserRequest.getCountry());
        user.setImage(editProfileUserRequest.getImage());
        user.setSociety(editProfileUserRequest.getSociety());
        this.userRepository.save(user);
        return  user;
    }
}
