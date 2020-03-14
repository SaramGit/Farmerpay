package com.mani.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mani.Utils.JwtUtil;
import com.mani.entity.AuthRequest;


@Controller
public class LoginController {
	
	@Autowired
	 private JwtUtil jwtUtil;
	  @Autowired
	   private AuthenticationManager manager;
	
	  @RequestMapping(value="/wish.htm",method=RequestMethod.GET)
	 public String welcomepage() {
		 return "welcome";
	 }
	  
	  @PostMapping(value="/authenticate.htm",consumes= {MediaType.APPLICATION_JSON_VALUE})
	    private @ResponseBody String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
	    System.out.println(authRequest);
		  try {
	    	 // manager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getCompanyId(), 
	    			//  authRequest.getPassword()));
	    	}catch (Exception e) {
				throw new Exception("authentication failed");
			}
		  System.out.println( jwtUtil.generateToken(authRequest.getCompanyId()));
	    	return jwtUtil.generateToken(authRequest.getCompanyId());
	    }

	
}
