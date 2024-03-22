package br.com.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import br.com.email.model.dto.AccountCredencialsDTO;
import br.com.email.model.dto.TokenDTO;
import br.com.email.repository.UserRepository;
import br.com.email.security.JwtTokenProvider;

@Service
public class AuthService {

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository repository;
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity signIn(AccountCredencialsDTO credencial) {
		try {
			var username = credencial.getUsername();
			var password = credencial.getPassword();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			var user = repository.findByUserName(username);
			var tokenResponse = new TokenDTO();
			if (user != null) {
				tokenResponse = tokenProvider.createAccessToken(user.getUsername(), user.getRoles());
			}else {
				throw new UsernameNotFoundException("Username " + username + " Not Found!");
			}
			return ResponseEntity.ok(tokenResponse);
		}catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Username/Password!");
		}
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity refreshToken(String username, String refreshToken) {
		var user = repository.findByUserName(username);
		var tokenResponse = new TokenDTO();
		if (user != null) {
			tokenResponse = tokenProvider.refreshToken(refreshToken);
		}else {
			throw new UsernameNotFoundException("Username " + username + " Not Found!");
		}
		return ResponseEntity.ok(tokenResponse);
	}
	
	public boolean checkIfParamsIsNotNull(AccountCredencialsDTO data) {
		return data == null || data.getUsername() == null || data.getUsername().isBlank()
				|| data.getPassword() == null || data.getPassword().isBlank();
	}
	
	public boolean checkIfRefreshTokenIsValid(String username, String refreshToken) {
		return refreshToken == null || refreshToken.isBlank() || username == null || username.isBlank();
	}
}
