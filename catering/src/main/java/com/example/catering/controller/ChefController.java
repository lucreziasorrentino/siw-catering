package com.example.catering.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.catering.controller.validator.ChefValidator;
import com.example.catering.model.Chef;
import com.example.catering.service.ChefService;

@Controller
public class ChefController {
	@Autowired
	private ChefService chefService;

	@Autowired
	private ChefValidator chefValidator;

	
	@PostMapping("/admin/chefForm")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model) {
		this.chefValidator.validate(chef, bindingResult);
		if(!bindingResult.hasErrors()) {
			chefService.save(chef);
			model.addAttribute("chef", chef);
			return "chef.html";
		}
		return "chefForm.html";
	}
	
	@GetMapping("/public/listaChef")
	public String getListaChef(Model model) {
		List <Chef> listaChef = chefService.findAll();
		model.addAttribute("listaChef", listaChef);
		return "listaChef.html";
	}

	@GetMapping("/public/chef/{id}")
	public String getChef(@PathVariable("id") Long id, Model model) {
		Chef chef = chefService.findById(id);
		model.addAttribute("chef", chef);
		return "chef.html";
	}
	
	@GetMapping("/admin/chefForm")
	public String getChef(Model model) {
		model.addAttribute("chef", new Chef());
		return "chefForm.html";
	}
	
	@GetMapping("/admin/deleteChef/{id}")
	public String toDeleteChef(@PathVariable("id") Long id, Model model) {
		model.addAttribute("chef", chefService.findById(id));
		return "eliminaChef.html";
	}
	
	@PostMapping("/admin/deleteChef/{id}")
	public String deleteChef(@PathVariable("id") Long id, Model model) {
		chefService.deleteById(id);
		model.addAttribute("listaChef", chefService.findAll());
		return "listaChef.html";
	}
	
	@GetMapping("/admin/modificaChefForm/{id}")
	public String getChefForm(@PathVariable Long id, Model model) {
		model.addAttribute("chef", chefService.findById(id));
		return "modificaChefForm.html";
	}
	
	@Transactional
	@PostMapping("/admin/modifica/chef/{id}")
	public String modificaChef(@PathVariable Long id, @Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResults, Model model) {
		String nextPage;
		if(!bindingResults.hasErrors()) {
			Chef vecchioChef = chefService.findById(id);
			vecchioChef.setId(chef.getId());
			vecchioChef.setNome(chef.getNome());
			vecchioChef.setCognome(chef.getCognome());
			vecchioChef.setNazionalita(chef.getNazionalita());
			this.chefService.save(vecchioChef);
			model.addAttribute("chef", chef);
			model.addAttribute("chef", vecchioChef);
			nextPage = "chef.html";
		} else {
			nextPage = "modificaChefForm.html";
		}
		return nextPage;
	}

	
}
