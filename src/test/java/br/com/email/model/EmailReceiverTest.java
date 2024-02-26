package br.com.email.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.email.builder.EmailReceiverBuilder;
import br.com.email.helper.EmailValidator;

public class EmailReceiverTest {

	@Test
	public void checkingAValidEmail() {
		EmailReceiver emailReceiver = new EmailReceiver();
		emailReceiver = EmailReceiverBuilder.build();
		boolean validDestiny = EmailValidator.isValidEmail(emailReceiver.getDestiny());
		boolean validOrigin = EmailValidator.isValidEmail(emailReceiver.getOrigin());
		boolean validCopy = EmailValidator.isValidEmail(emailReceiver.getCopy());
		Assertions.assertAll("E-mail Válido", 
				() -> assertEquals(validDestiny, true),
				() -> assertEquals(validOrigin, true),
				() -> assertEquals(validCopy, true));
	}
}
