package br.com.email.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.email.exception.EmailNotFoundException;
import br.com.email.helper.EmailValidator;
import br.com.email.model.EmailReceiver;
import br.com.email.repository.EmailReceiverRepository;

@Service
public class EmailReceiverService {

	@Autowired
	private EmailReceiverRepository repository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	private static final Logger logger = LoggerFactory.getLogger(EmailReceiverService.class);
	
	public void sendEmail(Long codigo) {
		EmailReceiver emailReceiver = new EmailReceiver();
		emailReceiver = searchEmail(codigo);
		try {
			if(emailReceiver != null) {
				MimeMessage message = createMessage(emailReceiver);
				mailSender.send(message);
				logger.info("E-mail " + emailReceiver.getId() + " Enviado.");
			}else {
				throw new EmailNotFoundException("E-mail Não Encontrado.");
			}
		} catch (Exception e) {
			logger.error("Erro ao Enviar Email. ERRO: " + e.getMessage());
		}
	}
	
	public MimeMessage createMessage(EmailReceiver emailReceiver) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			configureEmail(helper, emailReceiver);
		} catch (MessagingException e) {
			logger.error("Erro ao Criar Mensagem. - " + e.getMessage());
		}
		return message;
	}
	
	public void configureEmail(MimeMessageHelper helper, EmailReceiver emailReceiver) {
		try {
			configureOriginMail(helper, emailReceiver);
			configureDestinyMail(helper, emailReceiver);
			configureCopyMail(helper, emailReceiver);
			configureMailBodyAndSubject(helper, emailReceiver);
		} catch (Exception e) {
			logger.error("Erro ao Configurar o E-mail " + emailReceiver.getId() + ". - " + e.getMessage());
		}
	}
	
	public void configureMailBodyAndSubject(MimeMessageHelper helper, EmailReceiver emailReceiver) {
		try {
			helper.setSubject(emailReceiver.getSubject());
			if(checkHtml(emailReceiver.getMailBody())){
				helper.setText(emailReceiver.getMailBody(), true);
			}else {
				helper.setText(emailReceiver.getMailBody());
			}
		} catch (Exception e) {
			logger.error("Erro ao Configurar o Corpo / Assunto do E-mail " + emailReceiver.getId() + ". - " + e.getMessage());
		}
	}

	public boolean checkHtml(String mailBody) {
		String regex = "<[^>]+>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mailBody);
		return matcher.find();
	}

	public void configureOriginMail(MimeMessageHelper helper, EmailReceiver emailReceiver) {
		boolean validMailOrigin = EmailValidator.isValidEmail(emailReceiver.getOrigin());
		try {
			if(validMailOrigin) {
				helper.setFrom(emailReceiver.getOrigin());
			}
		}catch (Exception e) {
			logger.error("Erro ao Configurar o Remetente do E-mail " + emailReceiver.getId() + ". - " + e.getMessage());
		}
	}
	
	public void configureDestinyMail(MimeMessageHelper helper, EmailReceiver emailReceiver) {
		boolean validMailDestiny = EmailValidator.isValidEmail(emailReceiver.getDestiny());
		try {
			if(validMailDestiny) {
				helper.setTo(emailReceiver.getDestiny());
			}
		} catch (Exception e) {
			logger.error("Erro ao Configurar o Destinatário do E-mail " + emailReceiver.getId() + ". - " + e.getMessage());
		}
	}
	
	public void configureCopyMail(MimeMessageHelper helper, EmailReceiver emailReceiver) {
		boolean validMailCopy = EmailValidator.isValidEmail(emailReceiver.getCopy());
		try {
			if(validMailCopy) {
				helper.setCc(emailReceiver.getCopy());
			}
		}catch (Exception e) {
			logger.error("Erro ao Configurar a Cópia do E-mail " + emailReceiver.getId() + ". - " + e.getMessage());
		}
	}

	public List<EmailReceiver> findEmails(){
		List<EmailReceiver> list = new ArrayList<>();
		try {
			list = repository.findAll();
		} catch (Exception e) {
			throw new EmailNotFoundException("Lista não Encontrada." + e.getMessage());
		}
		return list;
	}
	
	public EmailReceiver searchEmail (Long codigo) {
		EmailReceiver model = repository.findById(codigo)
					  .orElseThrow(() -> new EmailNotFoundException("E-mail Não encontrado."));
		return model;
	}
}
