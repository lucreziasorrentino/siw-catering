package com.example.catering.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.catering.controller.validator.IngredienteValidator;
import com.example.catering.model.Ingrediente;
import com.example.catering.model.Ingrediente;
import com.example.catering.model.Ingrediente;
import com.example.catering.model.Piatto;
import com.example.catering.service.IngredienteService;
import com.example.catering.service.PiattoService;

@Controller
public class IngredienteController {
	@Autowired
	private IngredienteService ingredienteService;

	@Autowired
	private IngredienteValidator ingredienteValidator;

	
	@PostMapping("/admin/ingredienteForm")
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {
		this.ingredienteValidator.validate(ingrediente,bindingResult);
		if(!bindingResult.hasErrors()) {
			ingredienteService.save(ingrediente);
			model.addAttribute("ingrediente", ingrediente);
			return "ingrediente.html";
		}
		return "ingredienteForm.html";
	}
	
	@GetMapping("/admin/ingredienteForm")
	public String getIngrediente(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "ingredienteForm.html";
	}
	
	@GetMapping("/public/ingrediente/{id}")
	public String getIngrediente(@PathVariable("id") Long id, Model model) {
		Ingrediente ingrediente = ingredienteService.findById(id);
		model.addAttribute("ingrediente", ingrediente);
		return "ingrediente.html";
	}

	@GetMapping("/admin/ingredienti") //avere la lista di tutti gli ingredienti interessa solo all'admin
	public String getIngredienti(Model model) {
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "ingredienti.html";
	}
	
	@GetMapping("/admin/modificaIngredienteForm/{id}")
	public String getBuffetForm(@PathVariable Long id, Model model) {
		model.addAttribute("ingrediente", ingredienteService.findById(id));
		return "modificaIngredienteForm.html";
	}

	@Transactional
	@PostMapping("/admin/modifica/ingrediente/{id}")
	public String modificaIngrediente(@PathVariable Long id, @Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResults, Model model) {
		String nextPage;
		if(!bindingResults.hasErrors()) {
			Ingrediente vecchioIngrediente = ingredienteService.findById(id);
			vecchioIngrediente.setId(ingrediente.getId());
			vecchioIngrediente.setNome(ingrediente.getNome());
			vecchioIngrediente.setDescrizione(ingrediente.getDescrizione());
			vecchioIngrediente.setOrigine(ingrediente.getOrigine());
			this.ingredienteService.save(vecchioIngrediente);
			model.addAttribute("ingrediente", ingrediente);
			nextPage = "ingrediente.html";
		} else {
			nextPage = "modificaIngredienteForm.html";
		}
		return nextPage;
	}
	
	@GetMapping("/admin/deleteIngrediente/{id}")
	public String toDeleteIngrediente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingrediente", ingredienteService.findById(id));
		return "eliminaIngrediente.html";
	}
	
	@PostMapping("/admin/deleteIngrediente/{id}")
	public String deleteIngrediente(@PathVariable("id") Long id, Model model) {
		ingredienteService.deleteById(id);
		model.addAttribute("ingredienti", ingredienteService.findAll());
		return "ingredienti.html";
	}
	
}
