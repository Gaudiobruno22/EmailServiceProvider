package br.com.email.response;

import java.util.List;

import br.com.email.model.EmailReceiver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailResponse {

	private List<EmailReceiver> list;
	private String errorMessage;
}
