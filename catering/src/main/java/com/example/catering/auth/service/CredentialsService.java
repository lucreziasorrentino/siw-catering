package com.example.catering.auth.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.catering.auth.models.Credentials;
import com.example.catering.auth.repository.CredentialsRepository;

import java.util.Optional;

@Service
public class CredentialsService {

	@Autowired
	protected PasswordEncoder passwordEncoder;

	@Autowired
	private CredentialsRepository credentialsRepository;

	public Credentials getCredentials(Long id){
		Optional<Credentials> p = credentialsRepository.findById(id);
		if (p.isPresent()) return p.get();
		return null;
	}
	public Credentials getCredentials(String username){
		Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
		return result.orElse(null);
	}
	public Credentials saveCredentials(Credentials credentials){
		credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
		return credentialsRepository.save(credentials);
	}

}

