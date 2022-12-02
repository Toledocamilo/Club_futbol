package com.futbol.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		UserDetails usuario1 = User.withUsername("admin")
									.password("$2a$10$99n5P6azTLH8sQtZgO69X.02rWmrU/V2Bkeo/CAH9qhJ3u0I1vG1G")
									.roles("ADMIN")
									.build();
		return new InMemoryUserDetailsManager(usuario1);
	}								
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().anyRequest().authenticated().and().formLogin()
			.loginPage("/login").loginProcessingUrl("/ingresar").permitAll();
	}

}
