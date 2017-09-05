package ma.octo.agritech.controllers;

import ma.octo.agritech.domains.Production;
import ma.octo.agritech.domains.User;
import ma.octo.agritech.domains.UserStats;
import ma.octo.agritech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UserApiController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<User> index() {
        return this.userService.getAll();
    }

    @GetMapping(value = "/productions", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Production> getMyProductions() {
        return this.userService.getAllAuthProductions();
    }

//    @PreAuthorize("#oauth2.hasScope('api:read')")
    @GetMapping(value = "/info", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public User getUserInfo(final Principal principal) {

        return userService.getByUsername(principal.getName());

    }

    @GetMapping(value = "/stats", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public UserStats userCount() {

        return this.userService.getUserStats();
    }


}