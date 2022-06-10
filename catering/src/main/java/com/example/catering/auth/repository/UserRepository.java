package it.uniroma3.siw.siwcateringservice.auth.repository;

import it.uniroma3.siw.siwcateringservice.auth.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
