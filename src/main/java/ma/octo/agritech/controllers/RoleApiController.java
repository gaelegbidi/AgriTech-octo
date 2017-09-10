package ma.octo.agritech.controllers;

import ma.octo.agritech.domains.Role;
import ma.octo.agritech.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "api/roles")
@RestController
public class RoleApiController {

    @Autowired
    private RoleService roleService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Role> index() {
        return this.roleService.getAll();
    }


}



