package br.com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.email.model.dto.AccountCredencialsDTO;
import br.com.email.service.AuthService;

@RestController
@RequestMapping(value = "authorization/auth")
public class AuthorizationController {

	@Autowired
	private AuthService service;
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/signin")
	public ResponseEntity signIn(@RequestBody AccountCredencialsDTO credencial) {
		if (service.checkIfParamsIsNotNull(credencial)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client Request.");
		var token = service.signIn(credencial);
		if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client Request.");
		return token;
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity refreshTokEntity(@PathVariable("username") String username, @RequestHeader("Authorization") String refreshToken) {
		if(service.checkIfRefreshTokenIsValid(username, refreshToken)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client Request.");
		var token = service.refreshToken(username, refreshToken);
		if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client Request.");
		return token;
	}
}
