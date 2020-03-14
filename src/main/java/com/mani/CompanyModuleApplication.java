package com.mani;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.omg.CORBA.portable.Streamable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mani.Repository.CompanyRepository;
import com.mani.entity.Company;

@SpringBootApplication
public class CompanyModuleApplication {

	@Autowired
	CompanyRepository repository;
	
	@PostConstruct
	  public void init() {
	    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	  }
	
	@PostConstruct
	public void initCompany() {
		List<Company> loginlist=Stream.of(new Company("Cp001","785Fgjiokluiip#12"),
				new Company("cp002","45685iofpggui=#jk"),
				new Company("cp003","9749rhfhfghifg=#5")).collect(Collectors.toList());
		repository.saveAll(loginlist);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CompanyModuleApplication.class, args);
	}

	@Bean
	public PasswordEncoder PasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedMethods("*").allowedOrigins("*").allowCredentials(true);
			}
		};
	}

	@Bean
	HttpHeaders createHeaders(){
		   return new HttpHeaders() {{
		         set( "Authorization", "Basic U2FuZ3JhbToxMjM0NQ==");
		      }};
		}
	
}
