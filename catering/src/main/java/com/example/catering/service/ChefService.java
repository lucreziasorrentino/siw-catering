package com.example.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.catering.model.Chef;
import com.example.catering.repository.ChefRepository;

@Service
public class ChefService {
	@Autowired
	private ChefRepository cr;
	
	@Transactional
	public void save(Chef chef) {
		cr.save(chef);
	}
	
	public Chef findById(Long id) {
		return cr.findById(id).get();
	}
	
	public List<Chef> findAll(){
		List<Chef> listaChef = new ArrayList<Chef>();
		for(Chef c : cr.findAll()) {
			listaChef.add(c);
		}
		return listaChef;
	}
	
	public boolean alreadyExists(Chef chef) {
		return cr.existsByNomeAndCognomeAndNazionalita(chef.getNome(), chef.getCognome(), chef.getNazionalita());
	}
	
	
	public void deleteById(Long id) {
		cr.deleteById(id);
	}
}
