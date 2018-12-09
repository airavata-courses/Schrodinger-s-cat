package com.foodcode.microservice.restfuluserauthentication.configuration;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.foodcode.microservice.restfuluserauthentication.persistence.repository.UserRepository;
import com.foodcode.microservice.restfuluserauthentication.service.UserService;

import org.springframework.security.core.Authentication;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class JWTAuthenticationFilter extends GenericFilterBean{
	
	private UserRepository userRepository;
	
	public JWTAuthenticationFilter(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Override
	public void doFilter(ServletRequest request,
			ServletResponse response,
			FilterChain filterChain)
					throws IOException, ServletException {
		System.out.println("Coming here? ");
		Authentication authentication = TokenAuthenticationService
				.getAuthentication((HttpServletRequest)request);

		SecurityContextHolder.getContext()
		.setAuthentication(authentication);
		filterChain.doFilter(request,response);
	}
}
