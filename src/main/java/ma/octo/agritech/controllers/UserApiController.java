package ma.octo.agritech.controllers;

import ma.octo.agritech.domains.User;
import ma.octo.agritech.repositories.UserRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
	public User getUserInfo(final Principal principal) {

		return repository.findOneByUsername(principal.getName());

	}

}