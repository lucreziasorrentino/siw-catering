package com.example.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.catering.model.Piatto;
import com.example.catering.repository.PiattoRepository;

@Service
public class PiattoService {
	@Autowired
	private PiattoRepository pr;
	
	//ci pensa springboot:
	@Transactional
	public void save(Piatto piatto) {	//transazionale 
		pr.save(piatto);
	}
	
	//interrogazione non transazionale
	public Piatto findById(Long id) {
		return pr.findById(id).get();
	}
	
	public List<Piatto> findAll(){
		List<Piatto> piatti = new ArrayList<Piatto>();
		for(Piatto p : pr.findAll()) {
			piatti.add(p);
		}
		return piatti;
	}
	
	public boolean alreadyExists(Piatto piatto) {
		return pr.existsByNomeAndDescrizione(piatto.getNome(), piatto.getDescrizione());
	}
	
	
	public void deleteById(Long id) {
		pr.deleteById(id);
	}
}
