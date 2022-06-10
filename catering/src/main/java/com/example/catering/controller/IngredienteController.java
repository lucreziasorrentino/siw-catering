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

	@PostMapping("/admin/ingredienteForm")
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {
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

	
	
}
