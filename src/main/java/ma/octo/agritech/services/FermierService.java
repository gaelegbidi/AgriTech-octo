//package ma.octo.agritech.services;
//
//import ma.octo.agritech.repositories.FermierRepository;
//import ma.octo.agritech.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class FermierService implements UserDetailsService {
//
//    @Autowired
//    private FermierRepository FermierRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return (UserDetails) FermierRepository.findOneByUsername(username);
//    }
//}
