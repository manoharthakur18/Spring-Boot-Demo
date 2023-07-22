package com.app.boot.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
		HttpSecurity http1 = http.authorizeHttpRequests((requests) -> {
			requests.requestMatchers("/authenticate", "/adduser").permitAll();
		}).authorizeHttpRequests((requests) -> {
			try {
//				requests.requestMatchers("/*").authenticated().anyRequest().authenticated();
				requests.requestMatchers(HttpMethod.GET, "/getproducts").hasRole("ADMIN");
				requests.requestMatchers(HttpMethod.POST, "/saveproduct").hasRole("ADMIN");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).csrf(c -> c.disable()).httpBasic(Customizer.withDefaults());
		http1.addFilterAt(new CustomFilter(authManager), BasicAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	UserDetailsService userDetailsService() {
////		UserDetails user1 = User.builder().username("admin").password(passwordEncoder().encode("abcd")).roles("ADMIN")
////				.build();
////		UserDetails user2 = User.builder().username("user").password(passwordEncoder().encode("demo")).roles("USER")
////				.build();
////		return new InMemoryUserDetailsManager(user1, user2);
//
//		return new UserInfoUserDetailsService();
//	}

//	@Bean
//	AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder,
//			UserDetailsService userDetailsService) {
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//		authenticationProvider.setUserDetailsService(userDetailsService);
//		authenticationProvider.setPasswordEncoder(passwordEncoder);
//		return authenticationProvider;
//	}

	@Autowired
	private CustomAuthenticationProvider authProvider;

//	@Autowired
//	CustomFilter customFilter;

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(authProvider);
		return authenticationManagerBuilder.build();
	}

}
