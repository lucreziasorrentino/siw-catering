package com.example.catering.auth.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.catering.auth.models.Credentials;

import java.util.Optional;

@Repository
public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
	 Optional<Credentials> findByUsername(String username);

}
