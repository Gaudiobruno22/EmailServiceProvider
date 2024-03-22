package br.com.email.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.mail.internet.AddressException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.email.builder.EmailReceiverBuilder;
import br.com.email.helper.EmailValidator;

public class EmailReceiverTest {

	@Test
	@DisplayName("Validador de E-mails")
	public void shouldValidateEmailAddresses() {
		EmailReceiver emailReceiver = EmailReceiverBuilder.build();
		boolean validDestiny = EmailValidator.isValidEmail(emailReceiver.getDestiny());
		boolean validOrigin = EmailValidator.isValidEmail(emailReceiver.getOrigin());
		boolean validCopy = EmailValidator.isValidEmail(emailReceiver.getCopy());
		Assertions.assertAll("E-mail Válido", 
				() -> assertEquals(validDestiny, true),
				() -> assertEquals(validOrigin, false),
				() -> assertEquals(validCopy, false));
	}
	
    @Test
    @DisplayName("Lança Exceção Caso Destino Vazio.")
    public void shouldRejectUserWithoutEmailAddresses() {
        EmailReceiver emailReceiver = EmailReceiverBuilder.build();
        emailReceiver.setDestiny(null);

        AddressException ex = Assertions.assertThrows(AddressException.class, () ->
                EmailValidator.isValidEmail(emailReceiver.getDestiny()));

        Assertions.assertEquals(" - Missing final '@domain'", ex.getMessage());
    }
}
