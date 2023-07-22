package com.app.boot.securityConfig;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.WebFilterChainServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomFilter extends OncePerRequestFilter {

	public CustomFilter(AuthenticationManager authManager) {
		this.authenticationManager = authManager;
	}

	AuthenticationManager authenticationManager;
	private SecurityContextRepository securityContextRepository = new RequestAttributeSecurityContextRepository();
	private ServerAuthenticationSuccessHandler authenticationSuccessHandler = new WebFilterChainServerAuthenticationSuccessHandler();
	private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
			.getContextHolderStrategy();

	private RememberMeServices rememberMeServices = new NullRememberMeServices();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		request.authenticate(response);
		System.out.println("Headers name" + request.getHeaderNames());
		Enumeration<String> list = request.getHeaderNames();
		String authorization = request.getHeader("authorization");
		System.out.println(authorization);
		if (authorization.startsWith("Basic")) {
			String[] arr = authorization.split(" ");
			String token = arr[1];
			byte[] decoded = Base64.getDecoder().decode(token);
			String credentials = new String(decoded, StandardCharsets.UTF_8);
			System.out.println(credentials);
			String[] userAndPassword = credentials.split(":");
			Authentication auth = new UsernamePasswordAuthenticationToken(userAndPassword[0], userAndPassword[1]);
			Authentication auth1 = authenticationManager.authenticate(auth);
			System.out.println("Hii "+ userAndPassword[0] + userAndPassword[1]);

			SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
			context.setAuthentication(auth1);
			this.securityContextHolderStrategy.setContext(context);
			this.rememberMeServices.loginSuccess(request, response, auth1);
			this.securityContextRepository.saveContext(context, request, response);
			filterChain.doFilter(request, response);
		}
//		while (list.hasMoreElements()) {
//			System.out.println(list.nextElement());
//		
	

	}

}
