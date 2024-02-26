package br.com.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.email.model.EmailReceiver;

@Repository
public interface EmailReceiverRepository extends JpaRepository<EmailReceiver, Long>{

}
