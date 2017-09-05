package ma.octo.agritech.services;

import ma.octo.agritech.domains.Role;
import ma.octo.agritech.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        this.roleRepository.findAll().forEach(roles::add);
        return roles;
    }
}
