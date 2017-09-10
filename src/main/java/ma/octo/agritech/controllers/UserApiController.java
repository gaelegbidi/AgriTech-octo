package ma.octo.agritech.controllers;

import ma.octo.agritech.domains.Production;
import ma.octo.agritech.domains.User;
import ma.octo.agritech.domains.UserStats;
import ma.octo.agritech.requests.EditProfileUserRequest;
import ma.octo.agritech.requests.StoreUserRequest;
import ma.octo.agritech.requests.UpdateUserRequest;
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


    @GetMapping(value = "/info", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public User getUserInfo(final Principal principal) {

        return userService.getByUsername(principal.getName());

    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public User show(@PathVariable("id") Long id) {

        return userService.getById(id);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public User update(@PathVariable("id")  Long id, @RequestBody UpdateUserRequest updateUserRequest) {

        return userService.saveByUpdateRequest(id, updateUserRequest);

    }

    @PutMapping(value = "/editProfile", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public User editProfile( @RequestBody EditProfileUserRequest editProfileUserRequest) {

        return userService.saveByEditProfileRequest(editProfileUserRequest);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public void destroy(@PathVariable("id")  Long id) {
         this.userService.deleteById(id);
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