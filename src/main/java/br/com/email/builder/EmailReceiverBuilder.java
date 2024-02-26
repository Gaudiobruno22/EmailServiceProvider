package br.com.email.builder;

import java.time.LocalDate;

import br.com.email.model.EmailReceiver;
import lombok.Builder;

@Builder
public class EmailReceiverBuilder {

	
	public static EmailReceiver build() {
		return EmailReceiver.builder().subject("Assunto de Teste")
						.mailBody("Olá, meu Nome é Bruno. Este é um Corpo de E-mail Teste.")
						.origin("brunogmattos22@gmail.com")
						.destiny("brunogmattos22@gmail.com")
						.copy("")
						.recordingDate(LocalDate.now())
						.build();
	}
}
