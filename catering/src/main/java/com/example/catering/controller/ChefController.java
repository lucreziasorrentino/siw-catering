package com.example.catering.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.catering.model.Chef;
import com.example.catering.service.ChefService;

@Controller
public class ChefController {
	@Autowired
	private ChefService chefService;

	@PostMapping("/admin/chefForm")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model) {
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

	
}
