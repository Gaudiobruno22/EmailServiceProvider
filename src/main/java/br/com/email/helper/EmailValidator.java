package br.com.email.helper;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailValidator.class);
	
	public static boolean isValidEmail(String email) {
		boolean isValid = false;
		try {
			if(email.isBlank() || email == null) throw new AddressException("E-mail Vazio.");
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			isValid = true;		
		}catch (AddressException e) {
			logger.error(e.getMessage());
		}
		catch (NullPointerException e) {
			logger.error(e.getMessage());
		}
		return isValid;
	}
}
