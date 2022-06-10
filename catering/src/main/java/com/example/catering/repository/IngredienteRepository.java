package com.example.catering.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.catering.model.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {

	public boolean existsByNomeAndOrigineAndDescrizione(String nome, String origine, String descrizione);
	public void deleteById(Long id);
}
