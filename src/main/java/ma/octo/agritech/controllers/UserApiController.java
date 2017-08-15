package ma.octo.agritech.controllers;

import ma.octo.agritech.domains.User;
import ma.octo.agritech.domains.UserStats;
import ma.octo.agritech.repositories.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api")
public class UserApiController {

	private final UserRepository repository;

	public UserApiController(UserRepository repository) {
		this.repository = repository;
	}

	@PreAuthorize("#oauth2.hasScope('api:read')")
	@GetMapping(value = "/users/info", produces = APPLICATION_JSON_VALUE)
	public User getUserInfo(final Principal principal) {

		return repository.findOneByUsername(principal.getName());

	}

	@PreAuthorize("#oauth2.hasScope('api:read')")
	@GetMapping(value = "/users/stats", produces = APPLICATION_JSON_VALUE)
	public UserStats userCount() {

		List<User> users = (List<User>) this.repository.findAll();
		List<User> usersAcheteur = (List<User>) this.repository.findByRoles("Acheteur");
		List<User> usersPartenaire = (List<User>) this.repository.findByRoles("Partenaire");
		List<User> usersONG = (List<User>) this.repository.findByRoles("ONG");
		List<User> usersPublic = (List<User>) this.repository.findByRoles("Public");
		UserStats userStats = new UserStats(users.size(), usersAcheteur.size(), usersPartenaire.size(),
				usersPublic.size(), usersONG.size());
		return userStats;
	}
}