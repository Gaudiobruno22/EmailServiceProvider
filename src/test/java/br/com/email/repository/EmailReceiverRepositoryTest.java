package br.com.email.repository;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.email.model.EmailReceiver;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmailReceiverRepositoryTest {

	@Autowired
	private EmailReceiverRepository emailReceiverRepository;
	
	@Test
	public void savingEmailReceiver() {
		EmailReceiverRepository repository = Mockito.mock(EmailReceiverRepository.class);
		
		EmailReceiver emailReceiver = new EmailReceiver();
		emailReceiver.setSubject("Assunto Teste");
		emailReceiver.setDestiny("brunogmattos22@gmail.com");
		emailReceiver.setOrigin("bruno.mattos@dellavolpe.com.br");
		emailReceiver.setCopy("email@email.com.br");
		emailReceiver.setRecordingDate(LocalDate.now());
		
		when(repository.save(emailReceiver)).thenAnswer(invocation ->{
			EmailReceiver emailReceiverArgument = invocation.getArgument(0);
            emailReceiverArgument.setId(1L); // Simular a atribuição de um ID
            return emailReceiverArgument;
		});
		
		EmailReceiver savedEmail = repository.save(emailReceiver);
		Assertions.assertTrue(savedEmail.getId() > 0);
	}
}
