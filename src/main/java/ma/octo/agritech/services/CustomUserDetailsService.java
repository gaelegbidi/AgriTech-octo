package ma.octo.agritech.services;

import ma.octo.agritech.domains.CustomUserDetails;
import ma.octo.agritech.domains.Role;
import ma.octo.agritech.domains.User;
import ma.octo.agritech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = this.userRepository.findOneByUsername(username);

        userOptional.orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));

        return userOptional.map((User user) -> {
            List<Role> roles = this.userRepository.findRolesByUsername(username);
            user.setRoles(roles);
            return new CustomUserDetails(user);
        }).get();
    }
}
