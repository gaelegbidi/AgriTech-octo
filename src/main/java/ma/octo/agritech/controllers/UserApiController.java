package ma.octo.agritech.controllers;

import ma.octo.agritech.domains.User;
import ma.octo.agritech.domains.UserStats;
import ma.octo.agritech.repositories.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api")
public class UserApiController {

	private final UserRepository repository;

	public UserApiController(UserRepository repository) {
		this.repository = repository;
	}

	@PreAuthorize("#oauth2.hasScope('myapi:write')")
	@RequestMapping(value = "/users/info", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getUserInfo(final Principal principal) {

		Collection<User> users = repository.findByUsername(principal.getName());
		if (users.size() == 0) {
			return new ResponseEntity<>("{\"error\":\"Utilisateur introuvable\"}", UNAUTHORIZED);
		}

		User user = null;
		for (User userTmp : users) {
			user = userTmp;
			break;
		}
		String userString = String.format("{\"firstName\":\"%s\",\"lastName\":\"%s\",\"userName\":\"%s\"}",
				user.getFirstName(), user.getLastName(), user.getUsername());
		return new ResponseEntity<>(userString, OK);
	}




    }