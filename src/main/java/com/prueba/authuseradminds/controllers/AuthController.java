package com.prueba.authuseradminds.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.prueba.authuseradminds.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import com.prueba.authuseradminds.payload.request.LoginRequest;
import com.prueba.authuseradminds.payload.request.SignupRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/authuseradmin-data-service/v1/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	AuthService authService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
		return authService.authenticateUser(loginRequest, request);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return authService.registerUser(signUpRequest);
	}

	@PostMapping("/changep")
	public ResponseEntity<?> changeUserPassword(@Valid @RequestBody SignupRequest signUpRequest) {
		return authService.changeUserPassword(signUpRequest);
	}



	@GetMapping("/echo")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> authEcho(HttpServletRequest request) {
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@GetMapping("/valide")
	public ResponseEntity<?> valideToken(@RequestHeader (name="Authorization") String token, HttpServletRequest request) {
		return authService.valideToken(token, request);
	}

	@GetMapping("/expired")
	//@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> expired() {
		return new ResponseEntity<>("La sesión ha caducado", HttpStatus.OK);
	}
}
