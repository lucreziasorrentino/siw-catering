package com.example.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.catering.model.Ingrediente;
import com.example.catering.repository.IngredienteRepository;

@Service
public class IngredienteService {
	@Autowired
	private IngredienteRepository pr;
	
	//ci pensa springboot:
	@Transactional
	public void save(Ingrediente ingrediente) {	//transazionale 
		pr.save(ingrediente);
	}
	
	//interrogazione non transazionale
	public Ingrediente findById(Long id) {
		return pr.findById(id).get();
	}
	
	public List<Ingrediente> findAll(){
		List<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
		for(Ingrediente p : pr.findAll()) {
			ingredienti.add(p);
		}
		return ingredienti;
	}
	
	public boolean alreadyExists(Ingrediente ingrediente) {
		return pr.existsByNomeAndOrigineAndDescrizione(ingrediente.getNome(), ingrediente.getOrigine(), ingrediente.getDescrizione());
	}
	
	
	public void deleteById(Long id) {
		pr.deleteById(id);
	}
}
