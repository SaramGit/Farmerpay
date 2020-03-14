package com.mani.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mani.filter.JwtFilter;
//import com.mani.filter.JwtFilter;
import com.mani.service.CustomCompanyDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{


	@Autowired
	private CustomCompanyDetailsService service;
	@Autowired
	private JwtFilter jwtfilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service);
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.authorizeRequests().antMatchers("/*.htm").permitAll();
		/*
		 * .antMatchers(HttpMethod.OPTIONS,
		 * "/**").permitAll().anyRequest().authenticated().and()
		 * .exceptionHandling().and().sessionManagement().sessionCreationPolicy(
		 * SessionCreationPolicy.STATELESS);
		 */
		//http.addFilterAfter(jwtfilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
	}

}
