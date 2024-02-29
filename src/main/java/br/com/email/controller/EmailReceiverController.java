package br.com.email.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.email.exception.EmailNotFoundException;
import br.com.email.service.EmailReceiverService;

@RestController
@RequestMapping(value = "/api/v1/mail")
public class EmailReceiverController {

	@Autowired
	private EmailReceiverService emailService;
	
	private static final Logger logger = LoggerFactory.getLogger(EmailReceiverController.class);
	
	@GetMapping(value = "/send/{id}")
	public ResponseEntity<String> sendMails(@PathVariable Long id) {
		try {
			
			emailService.sendEmail(id);
			return ResponseEntity.ok("E-mail " + id + " Enviado.");
		}catch (EmailNotFoundException e) {
			logger.error("E-mail " + id + " Não Encontrado. " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email não Encontrado.");
		}catch (Exception e) {
			logger.error("Erro ao Enviar E-mail " + id + ".- " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email não Encontrado.");
		}
	}
	
	
}
