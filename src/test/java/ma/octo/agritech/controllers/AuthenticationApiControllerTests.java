package ma.octo.agritech.controllers;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class AuthenticationApiControllerTests {
	
	private static final String USER_USERNAME = "admin";
	private static final String USER_PASSWORD = "admin";
	private static final String GRANT_TYPE = "password";
	private static final String CLIENT_PASSWORD = "Pa123456";
	private static final String CLIENT_ID = "agritech-client";
	
	@Autowired
	private MockMvc mvc;

	@Test
	public void authenticationSuccessTest() throws Exception {

		this.mvc.perform(post("/oauth/token").with(httpBasic(CLIENT_ID, CLIENT_PASSWORD))
				.param("grant_type", GRANT_TYPE).param("username", USER_USERNAME).param("password", USER_PASSWORD))
				.andExpect(status().isOk()).andExpect(content().string(containsString("access_token")));
	}
	@Test
	public void authenticationAnotherUserTest() throws Exception {

		this.mvc.perform(post("/oauth/token").with(httpBasic(CLIENT_ID, CLIENT_PASSWORD))
				.param("grant_type", GRANT_TYPE).param("username", "Frodo").param("password", "dede"))
				.andExpect(status().isOk()).andExpect(content().string(containsString("access_token")));
	}

	@Test
	public void authenticationClientLoginIncorrectTest() throws Exception {

		this.mvc.perform(post("/oauth/token").with(httpBasic("agritech-client-1", CLIENT_PASSWORD))
				.param("grant_type", GRANT_TYPE).param("username", USER_USERNAME).param("password", USER_PASSWORD))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void authenticationClientPasswordIncorrectTest() throws Exception {

		this.mvc.perform(post("/oauth/token").with(httpBasic(CLIENT_ID, "Pa1234567"))
				.param("grant_type", GRANT_TYPE).param("username", USER_USERNAME).param("password", USER_PASSWORD))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void authenticationUserLoginIncorrectTest() throws Exception {

		this.mvc.perform(post("/oauth/token").with(httpBasic(CLIENT_ID, CLIENT_PASSWORD))
				.param("grant_type", GRANT_TYPE).param("username", "admin-1").param("password", USER_PASSWORD))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("{\"error\":\"invalid_grant\",\"error_description\":\"Bad credentials\"}"));
	}

	@Test
	public void authenticationUserPasswordIncorrectTest() throws Exception {

		this.mvc.perform(post("/oauth/token").with(httpBasic(CLIENT_ID, CLIENT_PASSWORD))
				.param("grant_type", GRANT_TYPE).param("username", USER_USERNAME).param("password", "admin-1"))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("{\"error\":\"invalid_grant\",\"error_description\":\"Bad credentials\"}"));
	}

	@Test
	public void authenticationGrantTypeIncorrectTest() throws Exception {

		this.mvc.perform(post("/oauth/token").with(httpBasic(CLIENT_ID, CLIENT_PASSWORD))
				.param("grant_type", "implicit").param("username", USER_USERNAME).param("password", USER_PASSWORD))
				.andExpect(status().isBadRequest()).andExpect(content().string(
						"{\"error\":\"invalid_grant\",\"error_description\":\"Implicit grant type not supported from token endpoint\"}"));
	}

}