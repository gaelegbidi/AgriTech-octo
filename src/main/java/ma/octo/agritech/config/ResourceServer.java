package ma.octo.agritech.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import static ma.octo.agritech.Application.RESOURCE_ID;

@Configuration
@EnableResourceServer
class ResourceServer extends ResourceServerConfigurerAdapter {
//
//		@Autowired
//		private TokenStore tokenStore;

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//			resources.tokenStore(tokenStore);
			resources.resourceId(RESOURCE_ID);
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers("/").permitAll()
					.and().authorizeRequests()
					.antMatchers("/console/**").permitAll()
					.and().authorizeRequests()
					.anyRequest().authenticated();

			http.csrf().disable();
			http.headers().frameOptions().disable();
		}

	}