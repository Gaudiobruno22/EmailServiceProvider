package br.com.email.builder;

import java.time.LocalDate;

import br.com.email.model.EmailReceiver;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class EmailReceiverBuilder {

	
	public static EmailReceiver build() {
		return EmailReceiver.builder()
							.subject("Assunto de Teste")
							.id(2L)
							.mailBody("Olá, meu Nome é Bruno. Este é um Corpo de E-mail Teste.")
							.origin(null)
							.destiny("brunogmattos22@gmail.com")
							.copy("")
							.recordingDate(LocalDate.now())
							.build();
	}
}
