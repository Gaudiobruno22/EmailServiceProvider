package br.com.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.email.model.EmailReceiver;

public interface EmailReceiverRepository extends JpaRepository<EmailReceiver, Long>{

}
