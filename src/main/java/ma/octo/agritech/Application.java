package ma.octo.agritech;

import ma.octo.agritech.config.security.CustomUserDetails;
import ma.octo.agritech.domains.User;
import ma.octo.agritech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@SpringBootApplication
public class Application {



	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository userRepo) throws Exception {

		if(userRepo.count()==0){
			userRepo.save(new User("Frodo", "Baggins", "ring bearer","dddd", "dede", "didi","cc","jiji","hello","grgr","koko","Admin"));
		}
		builder.userDetailsService((String username) -> {
            List<User> users = userRepo.findByUsername(username);
            User u = users.size()==1 ? users.get(0) : null;
            return new CustomUserDetails(u);
        });

//		builder.userDetailsService(new UserDetailsService() {
//			@Override
//			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//				List<User> users = userRepo.findByUsername(username);
//				User u = users.size()==1 ? users.get(0) : null;
//				return new CustomUserDetails(u);
//			}
//		});
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//			System.out.println("App ready ");
//		};
//	}


//	@Configuration
//	@EnableWebSecurity
//	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//	protected static class AuthServerConfig extends WebSecurityConfigurerAdapter {
//		@Autowired
//		private DataSource dataSource;
//
//		@Autowired
//		public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
//			auth.jdbcAuthentication().dataSource(dataSource)
//			.withUser("admin").password("admin").authorities("admin").and()
//			.withUser("alice").password("password").authorities("user").and()
//			.withUser("bob").password("password").authorities("user").and()
//			.withUser("eve").password("password").authorities("user");
//		}
//
//		@Bean
//		@Override
//		public UserDetailsService userDetailsServiceBean() throws Exception {
//			return super.userDetailsServiceBean();
//		}
//	}
//
//	@Configuration
//	@EnableResourceServer
//	protected static class ResourceServer extends ResourceServerConfigurerAdapter {
//
//		@Autowired
//		private TokenStore tokenStore;
//
//		@Override
//		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//			resources.tokenStore(tokenStore);
//		}
//
//		@Override
//		public void configure(HttpSecurity http) throws Exception {
//			http.authorizeRequests().anyRequest().authenticated();
//		}
//
//	}
//
//	@Configuration
//	@EnableAuthorizationServer
//	protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {
//
//		@Autowired
//		private AuthenticationManager auth;
//
//		@Autowired
//		private DataSource dataSource;
//
//		private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//		@Bean
//		public JdbcTokenStore tokenStore() {
//			return new JdbcTokenStore(dataSource);
//		}
//
//		@Bean
//		protected AuthorizationCodeServices authorizationCodeServices() {
//			return new JdbcAuthorizationCodeServices(dataSource);
//		}
//
//		@Override
//		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//			security.passwordEncoder(passwordEncoder);
//		}
//
//		@Override
//		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//			endpoints.authorizationCodeServices(authorizationCodeServices()).authenticationManager(auth)
//					.tokenStore(tokenStore()).approvalStoreDisabled();
//		}
//
//		@Override
//		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//			// @formatter:off
//			clients.jdbc(dataSource).passwordEncoder(passwordEncoder).withClient("agritech-client")
//					.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
//					.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT").scopes("read", "write", "trust")
//					.resourceIds("oauth2-resource").accessTokenValiditySeconds(60).and()
//					.withClient("my-client-with-registered-redirect").authorizedGrantTypes("authorization_code")
//					.authorities("ROLE_CLIENT").scopes("read", "trust").resourceIds("oauth2-resource")
//					.redirectUris("http://localhost:8080").and().withClient("my-client-with-secret")
//					.authorizedGrantTypes("client_credentials", "password").authorities("ROLE_CLIENT").scopes("read")
//					.resourceIds("oauth2-resource").secret("secret");
//			// @formatter:on
//		}
//
//	}
//
//	/******************/



}
