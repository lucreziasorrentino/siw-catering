package com.example.catering.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.catering.model.Piatto;

public interface PiattoRepository extends CrudRepository<Piatto, Long> {

	public boolean existsByNomeAndDescrizione(String nome, String descrizione);
	public void deleteById(Long id);
}
