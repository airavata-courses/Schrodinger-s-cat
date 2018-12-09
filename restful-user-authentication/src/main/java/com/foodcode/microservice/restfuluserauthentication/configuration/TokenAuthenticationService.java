package com.foodcode.microservice.restfuluserauthentication.configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security
.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.foodcode.microservice.restfuluserauthentication.persistence.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

public class TokenAuthenticationService {
	static final long EXPIRATIONTIME = 3600000; // 10 days
	static final String SECRET = "ThisIsASecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	static void addAuthentication(HttpServletResponse res, String username, String ID) throws IOException {
		String JWT = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(
	            "{ \"" + "id" + "\":\"" + ID + "\","+
	            "\"token\":"+"\""+JWT+"\""+
		"}"
	    );
	}

	static Authentication getAuthentication(HttpServletRequest request) {
		System.out.println("Hello"+request.getAttribute(HEADER_STRING));
		System.out.println("Hello"+request.getHeader(HEADER_STRING));
		String token = request.getHeader(HEADER_STRING);
		System.out.println("token:"+token);
		if (token != null) {
			// parse the token.
			String user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.addAll(AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
			return user != null ?
					new UsernamePasswordAuthenticationToken(user, null, authorities) :
						null;
		}
		return null;
	}

}
