package br.com.email.controller;

import java.util.ArrayList;
import java.util.List;

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
import br.com.email.model.EmailReceiver;
import br.com.email.response.EmailResponse;
import br.com.email.service.EmailReceiverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/mail")
@Api(description = "EndPoint Serviço Web Envio de E-mail.", tags = "Email Sender Service")
public class EmailReceiverController {

	@Autowired
	private EmailReceiverService emailService;
	
	private static final Logger logger = LoggerFactory.getLogger(EmailReceiverController.class);
	
	@ApiOperation(value = "EndPoint Principal de Envio de E-mail.")
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
	
	@ApiOperation(value = "Utilizado para verificar a relação dos E-mails Listados a serem Enviados.")
	@GetMapping(value = "/find/mails")
	public ResponseEntity<EmailResponse> findAllMails() {
		EmailResponse response = new EmailResponse();
		List<EmailReceiver> list = new ArrayList<>();
		try {
			list = emailService.findEmails();
			response.setList(list);
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			String error = "Erro ao Retornar E-mails." + e.getMessage();
			response.setErrorMessage(error);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}
}
