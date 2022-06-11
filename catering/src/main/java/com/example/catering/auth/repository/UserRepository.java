package com.example.catering.auth.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.catering.auth.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
