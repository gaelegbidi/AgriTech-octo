package ma.octo.agritech.services;

import ma.octo.agritech.domains.User;
import ma.octo.agritech.domains.UserStats;
import ma.octo.agritech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneByUsername(username);
    }

    /**
     * Get Users statistic
     *
     * @return UserStats
     */
    public UserStats getUserStats() {
        List<User> users = (List<User>) this.userRepository.findAll();
        List<User> usersAcheteur = this.userRepository.findByRoles("Acheteur");
        List<User> usersPartenaire = this.userRepository.findByRoles("Partenaire");
        List<User> usersONG = this.userRepository.findByRoles("ONG");
        List<User> usersPublic = this.userRepository.findByRoles("Public");
        return new UserStats(users.size(), usersAcheteur.size(), usersPartenaire.size(), usersPublic.size(), usersONG.size());
    }
}
