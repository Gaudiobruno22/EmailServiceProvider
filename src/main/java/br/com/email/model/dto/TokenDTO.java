package br.com.email.model.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TokenDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String username;
	private Boolean authenticaded;
	private Date created;
	private Date expiration;
	private String accessToken;
	private String refreshToken;
}
