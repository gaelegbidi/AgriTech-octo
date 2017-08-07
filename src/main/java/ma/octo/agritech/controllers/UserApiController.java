package ma.octo.agritech.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ma.octo.agritech.domains.User;
import ma.octo.agritech.domains.UserCredentials;
import ma.octo.agritech.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "api")
public class UserApiController {
    private final UserRepository repository;


    public UserApiController(UserRepository repository) {
        this.repository = repository;
    }


    @PostMapping(value = "/users/login", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody UserCredentials login) {

        String jwtToken;

        if (login.getUsername() == null || login.getPassword() == null) {
            return new ResponseEntity<>("Please fill in username and password",BAD_REQUEST);
        }

        String username = login.getUsername();
        String password = login.getPassword();

        List<User> users = this.repository.findByUsername(username);

        if(users.isEmpty()){
            return  new ResponseEntity<>("User email not found.",UNAUTHORIZED );
        }

        User user = this.repository.findByUsername(username).get(0);


        if (user == null) {
            return  new ResponseEntity<>("User email not found.",UNAUTHORIZED );
        }

        String pwd = user.getPassword();

        if (!BCrypt.checkpw(password,pwd)) {
            return new ResponseEntity<>("Invalid login. Please check your name and password.", UNAUTHORIZED);
        }

        jwtToken = Jwts.builder().setSubject(username).claim("roles", user.getRoles()).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

        return new ResponseEntity<>(jwtToken, OK);
    }



}