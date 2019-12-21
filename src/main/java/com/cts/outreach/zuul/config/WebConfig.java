package com.cts.outreach.zuul.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity(debug=true)
public class WebConfig extends WebSecurityConfigurerAdapter{
	
	private static final String[] CSRF_IGNORE = {"/authserver/**"};
	
	@Autowired
	private JwtConfig jwtConfig;
	
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		
		.and()
		.exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
		
		.and()
		.addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
		
		.authorizeRequests()
		.antMatchers("/authserver/**").permitAll()
		
		.and()
		.authorizeRequests()
		.anyRequest().authenticated()
		
		.and()
		.csrf()
		.disable();
//		.ignoringAntMatchers(CSRF_IGNORE)
//		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//		.and()
//		.addFilterAfter(new CsrfConfig(), CsrfFilter.class);
	}
	
	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName(CsrfConfig.CSRF_COOKIE_NAME);
		return repository;
	}

}
