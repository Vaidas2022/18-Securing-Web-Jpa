package javau9.securingjpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	UserDetailsService userDetailsService;
	
	public SecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	
	@Bean 
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/public/**").permitAll()
				.anyRequest().authenticated()
		)
		.formLogin((form) -> form
					.loginPage("/login")
					.defaultSuccessUrl("/home", true)
					.permitAll()
		)
		.logout((logout) -> logout
				.permitAll()
		);
		
		return http.build();		
	}
		
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
