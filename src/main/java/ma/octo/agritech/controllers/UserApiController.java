package ma.octo.agritech.controllers;

import ma.octo.agritech.Requests.StoreUserRequest;
import ma.octo.agritech.domains.Negociation;
import ma.octo.agritech.domains.Production;
import ma.octo.agritech.domains.User;
import ma.octo.agritech.domains.UserStats;
import ma.octo.agritech.services.StorageService;
import ma.octo.agritech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UserApiController {

//    private final StorageService storageService;
//
//    @Autowired
//    public UserApiController(StorageService storageService) {
//        this.storageService = storageService;
//    }

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<User> index() {
        return this.userService.getAll();
    }

    @PreAuthorize("hasRole('ROLE_AGRICULTEUR')")
    @GetMapping(value = "/productions", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Production> getMyProductions() {
        return this.userService.getAllAuthProductions();
    }

//    @GetMapping(value = "/negociations", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    public List<Negociation> getMyNegociations() {
//        return this.userService.getAllAuthNegociations();
//    }

//    @PreAuthorize("#oauth2.hasScope('api:read')")
    @GetMapping(value = "/info", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public User getUserInfo(final Principal principal) {

        return userService.getByUsername(principal.getName());

    }

    @GetMapping(value = "/stats", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public UserStats userCount() {

        return this.userService.getUserStats();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public User store(@RequestBody StoreUserRequest storeUserRequest) {
        return this.userService.saveByStoreRequest(storeUserRequest);
    }

}