package br.com.primeiroprojetospring.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.com.primeiroprojetospring.service.CurrentUserDetailsService;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private final CurrentUserDetailsService currentUserDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager 
			authenticationManager,
			CurrentUserDetailsService currentUserDetailsService) {
		super(authenticationManager);
		this.currentUserDetailsService = currentUserDetailsService;

	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String token = request.getHeader(
				ESecurityConstants.HEADER.desc());
		if (token == null 
				|| !token.startsWith(ESecurityConstants.TOKEN_PREFIX.desc())) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authenticationToken = 
				getAuthenticationToken(request, token);
		SecurityContextHolder.getContext()
		.setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthenticationToken(
			HttpServletRequest request, String token) {

		if (token == null) //double ckeck
			return null;

		String userName = Jwts.parser().setSigningKey(
				ESecurityConstants.SECRET.desc())
				.parseClaimsJws(token.replace(
						//Removendo Prefix "Bearer " que vem do HEADER, 
						//deixando apenas o token
						ESecurityConstants.TOKEN_PREFIX.desc(), ""))
				.getBody().getSubject();

		UserDetails userDetails = currentUserDetailsService
				.loadUserByUsername(userName);
		return userName != null ? new UsernamePasswordAuthenticationToken(
				userName, null, userDetails.getAuthorities()) : null;

	}

}