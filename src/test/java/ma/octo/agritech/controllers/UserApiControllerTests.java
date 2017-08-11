package ma.octo.agritech.controllers;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserApiControllerTests {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private OAuthHelper helper;

	@Test
	public void userInfoTest() throws Exception {
		RequestPostProcessor bearerToken = helper.bearerToken("agritech-client", "admin");

		this.mvc.perform(get("/api/users/info").with(bearerToken).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(content().string(
						"{\"id\":1,\"username\":\"admin\",\"firstName\":null,\"lastName\":null,\"email\":null,\"phone\":null,\"address\":null,\"city\":null,\"country\":null,\"function\":null,\"society\":null,\"roles\":null,\"enabled\":true,\"authorities\":[],\"accountNonLocked\":true,\"accountNonExpired\":true,\"credentialsNonExpired\":true}"));
	}

}