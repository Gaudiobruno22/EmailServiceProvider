package br.com.email.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class AccountCredencialsDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
}
