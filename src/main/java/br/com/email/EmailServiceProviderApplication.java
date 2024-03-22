package br.com.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.email.helper.EncodeGenerator;

@SpringBootApplication
public class EmailServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceProviderApplication.class, args);
        EncodeGenerator.passwordEncoder();
	}
}
