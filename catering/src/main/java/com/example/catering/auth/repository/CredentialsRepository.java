package it.uniroma3.siw.siwcateringservice.auth.repository;

import it.uniroma3.siw.siwcateringservice.auth.models.Credentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
	 Optional<Credentials> findByUsername(String username);

}
