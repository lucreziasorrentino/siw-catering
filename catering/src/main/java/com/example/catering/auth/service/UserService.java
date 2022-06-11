package com.example.catering.auth.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.catering.auth.models.User;
import com.example.catering.auth.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUser (Long id) {
		Optional<User> p = userRepository.findById(id);
		if (p.isPresent()) return p.get();
		return null;
	}

	public User saveUser (User user) {
		return userRepository.save(user);
	}

}
