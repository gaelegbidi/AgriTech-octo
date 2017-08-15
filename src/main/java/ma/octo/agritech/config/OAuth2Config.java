package ma.octo.agritech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import javax.sql.DataSource;

import static ma.octo.agritech.Application.CLIENT_ID;
import static ma.octo.agritech.Application.CLIENT_PASSWORD;
import static ma.octo.agritech.Application.RESOURCE_ID;


/*
elle va nous permettre de configurer the servers authorisations
 */
@Configuration
@EnableAuthorizationServer
class OAuth2Config extends AuthorizationServerConfigurerAdapter {

//		@Autowired
//		private AuthenticationManager auth;

		@Autowired
		private AuthenticationManager authenticationManager;
		/*
		on a charger la db
		 */
		@Qualifier("dataSource")
		@Autowired
		private DataSource dataSource;

		private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//		@Autowired
//		@Qualifier("userDetailsService")
//		private UserDetailsService userDetailsService;
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

		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			security.passwordEncoder(passwordEncoder);
			security.checkTokenAccess("isAuthenticated()");
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//			endpoints.authorizationCodeServices(authorizationCodeServices()).authenticationManager(auth)
//					.tokenStore(tokenStore()).approvalStoreDisabled().userDetailsService(userDetailsService);
			endpoints.authenticationManager(authenticationManager);
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			// Enregistrer le client OAUTH
			clients.jdbc(dataSource)
					.passwordEncoder(passwordEncoder)
					.withClient(CLIENT_ID)
					.secret(CLIENT_PASSWORD)
					.authorizedGrantTypes("client_credentials","password", "authorization_code", "refresh_token", "implicit")
					.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
					.scopes("read", "write", "trust")
					.resourceIds(RESOURCE_ID)
					.accessTokenValiditySeconds(86400);//une journée
		}

	}
