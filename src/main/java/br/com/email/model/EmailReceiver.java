package br.com.email.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_EMAIL_RECEIVER")
public class EmailReceiver implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMAIL_RECEIVER_ID", columnDefinition = "BIGINT")
	private Long id;
	
	@Column(name = "EMAIL_RECEIVER_SUBJECT", columnDefinition = "VARCHAR(255)")
	private String subject;
	
	@Column(name = "EMAIL_RECEIVER_MAILBODY", columnDefinition = "TEXT")
	private String mailBody;
	
	@Column(name = "EMAIL_RECEIVER_ORIGIN", columnDefinition = "VARCHAR(500)")
	private String origin;
	
	@Column(name = "EMAIL_RECEIVER_DESTINY", columnDefinition = "VARCHAR(500)")
	private String destiny;
	
	@Column(name = "EMAIL_RECEIVER_COPY", columnDefinition = "VARCHAR(500)")
	private String copy;
	
	@Column(name = "EMAIL_RECEIVER_DATEREG", columnDefinition = "DATE")
	private LocalDate recordingDate;
}
