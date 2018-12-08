package com.foodcode.microservice.restfuluserauthentication.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodcode.microservice.restfuluserauthentication.persistence.model.User;
import com.foodcode.microservice.restfuluserauthentication.persistence.model.UserAuthenticationDetails;
import com.foodcode.microservice.restfuluserauthentication.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter{
	private UserService userService;
	public JWTLoginFilter(String url, AuthenticationManager authManager, UserService userService) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		this.userService=userService;
	}

	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest req, HttpServletResponse res)
					throws AuthenticationException, IOException, ServletException {
		UserAuthenticationDetails creds = new ObjectMapper()
				.readValue(req.getInputStream(), UserAuthenticationDetails.class);
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.addAll(AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						creds.getUsername(),
						creds.getPassword(),
						authorities
						)
				);
	}
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest req,
			HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		System.out.println("hello "+auth.getPrincipal());
		System.out.println("hello 2 "+auth.getName());
		
//		res.setIntHeader("id", );
		System.out.println("Hello");
		User userExists = userService.findUserByEmail(auth.getName().trim());
		System.out.println("Hello2");
		if (userExists != null) {
			
			TokenAuthenticationService
			.addAuthentication(res, auth.getName(), userExists.getId()+"");
		}
		else {
			TokenAuthenticationService
			.addAuthentication(res, auth.getName(),"");
		}
		
	}
}
