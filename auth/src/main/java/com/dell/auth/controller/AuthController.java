/**
 * 
 */
package com.dell.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dell.auth.model.AuthenticateRequest;
import com.dell.auth.model.AuthenticateResponse;
import com.dell.auth.service.MyUserDetailService;
import com.dell.auth.util.JwtUtil;

/**
 * @author bhardu
 * @Since Apr 27, 2020
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailService myUserDetailsService;

	@Autowired
	private JwtUtil myJwtToken;

	@RequestMapping("/")
	public String Welcome() {
		return ("Welcome to auth service.");
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> generateAutheticationToken(@RequestBody AuthenticateRequest authenticateRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticateRequest.getUsername(), authenticateRequest.getPassword()));

		} catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new Exception("Username or Password is incorrect", e);
		}

		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticateRequest.getUsername());

		String jwt = myJwtToken.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticateResponse(jwt));
	}

	@RequestMapping(value = "/validateAdminUrl", method = RequestMethod.GET)
	public void validateAdminUrl() {

	}

	@RequestMapping(value = "/validateUserUrl", method = RequestMethod.GET)
	public void validateUserUrl() {

	}

    @GetMapping(value = "/private")
    public String privateArea(){
        return "Private area";
    }


}
