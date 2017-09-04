package ma.octo.agritech.controllers;

import ma.octo.agritech.domains.User;
import ma.octo.agritech.domains.UserStats;
import ma.octo.agritech.repositories.UserRepository;
import ma.octo.agritech.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api")
public class UserApiController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserApiController(UserRepository repository, UserService userService) {
        this.userRepository = repository;
        this.userService = userService;
    }

    @PreAuthorize("#oauth2.hasScope('api:read')")
    @GetMapping(value = "/users/info", produces = APPLICATION_JSON_VALUE)
    public User getUserInfo(final Principal principal) {

        return userRepository.findOneByUsername(principal.getName());

    }

    @PreAuthorize("#oauth2.hasScope('api:read')")
    @GetMapping(value = "/users/stats", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserStats> userCount() {

        return new ResponseEntity<>(this.userService.getUserStats(), HttpStatus.OK);
    }


}