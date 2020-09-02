package com.insta.application.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insta.application.config.JWTTokenUtils;
import com.insta.application.data.JwtResponse;
import com.insta.application.data.LoginData;
import com.insta.application.data.ResetPasswordData;
import com.insta.application.data.UserSignInData;
import com.insta.application.model.ForgotPasswordData;
import com.insta.application.service.JwtUserDetailsService;


@RestController
public class LoginController {

	public static String role = "";
	@Autowired
	private AuthenticationManager authenticationManager;


	@Autowired
	private JWTTokenUtils jwtTokenUtil;


	@Autowired
	private JwtUserDetailsService userDetailsService;

	@PostMapping("/insta/login")
	public ResponseEntity<?> login(@RequestBody UserSignInData userData) throws Exception {
		LoginData loginData = new LoginData();

		authenticate(userData.getUserName(), userData.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(userData.getUserName());
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		
		  Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities(); 
		  boolean isAdmin;
		  boolean isPartner;
		  String role;
		  ArrayList<String> roles = new ArrayList<String>();
		  for(GrantedAuthority grantedAuthority : authorities) 
		  { 
			  roles.add(grantedAuthority.getAuthority());
		  }
		if(roles.contains("admin") || roles.contains("nonadmin"))
		{
			return ResponseEntity.ok(new JwtResponse(token,String.join(",", roles)));
		}
		else
		{
			throw new Exception ("User does not have required roles to login!");
			
		}
		
	}

	@PostMapping("/insta/forgotpassword")
	public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordData username) throws Exception 
	{
		if(username.getUsername().equals("admin"))
		{
			return ResponseEntity.ok("Success");
		}
		else
		{
			throw new Exception("User does not exist");
		}
	}
	
	@PostMapping("/insta/resetpassword")
	public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordData payload) throws Exception 
	{
		
			return ResponseEntity.ok("Success");
	}
	@PostMapping("/insta/nonadminlogin")
	public ResponseEntity<?> loginNonadmin(@RequestBody UserSignInData userData) throws Exception {
		LoginData loginData = new LoginData();
		System.out.println(userData.getPassword()+"--"+userData.getUserName());
		authenticate(userData.getUserName(), userData.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(userData.getUserName());
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		System.out.println("Token Generated");
		  Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities(); 
		  ArrayList<String> role = new ArrayList<String>(); 
		  for(GrantedAuthority grantedAuthority : authorities) 
		  { 
			  role.add(grantedAuthority.getAuthority());
			} 
		  if(!role.contains("agent") && !role.contains("partner"))
		  {
			  throw new Exception("User does not have required role to login");
		  }
		return ResponseEntity.ok(new JwtResponse(token,String.join(",", role)));
	}


	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
