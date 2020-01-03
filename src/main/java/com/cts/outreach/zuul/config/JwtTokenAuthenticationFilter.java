package com.cts.outreach.zuul.config;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
	
	Logger LOGGER = LoggerFactory.getLogger(JwtTokenAuthenticationFilter.class);
	
	@Autowired
	private JwtConfig jwtConfig;
	
	public JwtTokenAuthenticationFilter(JwtConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		LOGGER.info("in jwttoken " + jwtConfig.getHeader());
		String header = request.getHeader(jwtConfig.getHeader());
		LOGGER.info("header in jwttoken " + header);
		if(header == null || !header.startsWith(jwtConfig.getPrefix())) {
			chain.doFilter(request, response);
			return;
		}

		String token = header.replace(jwtConfig.getPrefix(), "");
		
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(jwtConfig.getSecret().getBytes())
					.parseClaimsJws(token)
					.getBody();
			
			String username = claims.getSubject();
			if(username != null) {
				@SuppressWarnings("unchecked")
				List<String> authorities = (List<String>) claims.get("authorities");
				
				LOGGER.info("Authorities " + authorities);

				 UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
								 username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

				 SecurityContextHolder.getContext().setAuthentication(auth);
			}
			
		} catch (Exception e) {
			LOGGER.info("in jwttoken exception");
			SecurityContextHolder.clearContext();
		}
		
		chain.doFilter(request, response);
	}

}
