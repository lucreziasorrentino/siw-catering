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

import com.example.catering.model.Buffet;
import com.example.catering.service.BuffetService;
import com.example.catering.service.ChefService;
import com.example.catering.service.PiattoService;

@Controller
public class BuffetController {
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private PiattoService piattoService;
	
	@PostMapping("/admin/buffetForm")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			buffetService.save(buffet);
			model.addAttribute("buffet", buffet);
			return "buffet.html";
		}
		return "buffetForm.html";
	}
	
	//visualizza tutti i buffet
	@GetMapping("/public/listaBuffet")
	public String getListaBuffet(Model model) {
		List <Buffet> listaBuffet = buffetService.findAll();
		model.addAttribute("listaBuffet", listaBuffet);
		return "listaBuffet.html";
	}
	
	@GetMapping("/public/buffet/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		return "buffet.html";
	}
	
	@GetMapping("/admin/buffetForm")
	public String getBuffet(Model model) {
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("listaChef", this.chefService.findAll());
		model.addAttribute("piatti", this.piattoService.findAll());
		return "buffetForm.html";
	}

	
	
}
