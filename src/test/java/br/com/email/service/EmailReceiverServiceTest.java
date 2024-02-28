package br.com.email.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.javamail.JavaMailSender;

import br.com.email.builder.EmailReceiverBuilder;
import br.com.email.model.EmailReceiver;

public class EmailReceiverServiceTest {

	@Mock
	private JavaMailSender mailSender;
	
	@InjectMocks
	private EmailReceiverService emailService;
	
	private Long id;
	
	@BeforeEach
	void setUp() {
		id = 2L;
	}
	
	@Test
	public void testSendMailSuccess() {
		//Mockando um Objeto Existente.
		EmailReceiver emailReceiver = new EmailReceiver();
		emailReceiver = EmailReceiverBuilder.build();
		when(emailService.searchEmail(id)).thenReturn(emailReceiver);
		
		MimeMessage message = mock(MimeMessage.class);
		when(emailService.createMessage(emailReceiver)).thenReturn(message);
		
		emailService.sendEmail(id);
		verify(mailSender, times(1)).send(message);
	}
}
