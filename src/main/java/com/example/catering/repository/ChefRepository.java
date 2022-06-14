package com.example.catering.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.catering.model.Chef;

public interface ChefRepository extends CrudRepository<Chef, Long> {

	public boolean existsByNomeAndCognomeAndNazionalita(String nome, String cognome, String nazionalita);
	public void deleteById(Long id);
}
