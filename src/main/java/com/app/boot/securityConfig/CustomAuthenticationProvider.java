package com.app.boot.securityConfig;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsService userDetail;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String name = authentication.getName();
		String password = authentication.getCredentials().toString();

		UserDetails user = userDetail.loadUserByUsername(name);

		System.out.println("In custom authentication");

//		if (shouldAuthenticateAgainstThirdPartySystem()) {
//
//			// use the credentials
//			// and authenticate against the third-party system
		return new UsernamePasswordAuthenticationToken(name, user.getPassword(), user.getAuthorities());
//		} else {
//			return null;
//		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
