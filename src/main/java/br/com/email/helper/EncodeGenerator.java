package br.com.email.helper;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class EncodeGenerator {

	public static void passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());
        
        String result = passwordEncoder.encode("heroi");
        String base64 = Base64.getEncoder().encodeToString(result.getBytes());
        System.out.println("My hash " + result);
	}
}
