package com.mani.filter;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mani.Utils.JwtUtil;
import com.mani.service.CustomCompanyDetailsService;


@Component
public class JwtFilter extends GenericFilter {

	@Autowired
	JwtUtil jwtutil;

	@Autowired
	CustomCompanyDetailsService service;

	@Autowired
	HttpHeaders header;
	

	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		String authorizationHeader =null;
		if(((HttpServletRequest) request).getRequestURI().equals("/wish.htm")) {
			//header.add("Authorization","Basic U2FuZ3JhbToxMjM0NQ==");    
			authorizationHeader="Basic U2FuZ3JhbToxMjM0NQ==";
		}
		else {
		 authorizationHeader = ((HttpServletRequest) request).getHeader("Authorization");
		}
		/*
		 * if(authorizationHeader==null) { ServletContext servletContext =
		 * request.getServletContext(); WebApplicationContext webApplicationContext =
		 * WebApplicationContextUtils.getWebApplicationContext(servletContext);
		 * header.add("Authorization","Basic U2FuZ3JhbToxMjM0NQ=="); }else {
		 * authorizationHeader = ((HttpServletRequest)
		 * request).getHeader("Authorization"); }
		 */
		String userName = null;
		String token = null;

		System.out.println(authorizationHeader);
		if ( authorizationHeader!=null &authorizationHeader.contains("Bearer ")) {
			token = authorizationHeader.substring(7);
			userName = jwtutil.extractUsername(token);
		}

		if (userName != null & SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = service.loadUserByUsername(userName);
			if (jwtutil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
		
	}

	

}
