package com.example.catering.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.catering.model.Buffet;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {

	public boolean existsByNomeAndDescrizione(String nome, String descrizione);
	public void deleteById(Long id);
}
