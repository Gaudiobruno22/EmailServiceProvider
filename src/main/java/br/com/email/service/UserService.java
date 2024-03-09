package br.com.email.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.email.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Buscando um usuário pelo Código: " + username);
		var user = repository.findByUserName(username);
		if (user != null) {
			return user;
		}else {
			throw new UsernameNotFoundException("User Name " + username + " Not Found.");
		}
	}
}
