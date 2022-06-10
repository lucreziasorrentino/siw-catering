package com.example.catering.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.catering.model.Buffet;
import com.example.catering.model.Ingrediente;
import com.example.catering.model.Piatto;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {

	public boolean existsByNomeAndDescrizione(String nome, String descrizione);
	public void deleteById(Long id);
}
