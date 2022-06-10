package com.example.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.catering.model.Buffet;
import com.example.catering.repository.BuffetRepository;

@Service
public class BuffetService {
	@Autowired
	private BuffetRepository pr;
	
	//ci pensa springboot:
	@Transactional
	public void save(Buffet buffet) {	//transazionale 
		pr.save(buffet);
	}
	
	//interrogazione non transazionale
	public Buffet findById(Long id) {
		return pr.findById(id).get();
	}
	
	public List<Buffet> findAll(){
		List<Buffet> listaBuffet = new ArrayList<Buffet>();
		for(Buffet p : pr.findAll()) {
			listaBuffet.add(p);
		}
		return listaBuffet;
	}
	
	public boolean alreadyExists(Buffet buffet) {
		return pr.existsByNomeAndDescrizione(buffet.getNome(), buffet.getDescrizione());
	}
	
	
	public void deleteById(Long id) {
		pr.deleteById(id);
	}
}
