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

import com.example.catering.controller.validator.PiattoValidator;
import com.example.catering.model.Piatto;
import com.example.catering.service.IngredienteService;
import com.example.catering.service.PiattoService;

@Controller
public class PiattoController {
	
	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private PiattoValidator piattoValidator;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@PostMapping("/admin/piattoForm")
	public String addPersona(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResult, Model model) {
		this.piattoValidator.validate(piatto, bindingResult);
		if(!bindingResult.hasErrors()) {
			piattoService.save(piatto);
			model.addAttribute("piatto", piatto);
			return "piatto.html";
		}
		return "piattoForm.html";
	}
	
	@GetMapping("/public/piatti")
	public String getPiatti(Model model) {
		List <Piatto> piatti = piattoService.findAll();
		model.addAttribute("piatti", piatti);
		return "piatti.html";
	}
	
	@GetMapping("/public/piatto/{id}")
	public String getPiatto(@PathVariable("id") Long id, Model model) {
		Piatto piatto = piattoService.findById(id);
		model.addAttribute("piatto", piatto);
		return "piatto.html";
	}
	
	@GetMapping("/admin/piattoForm")
	public String getPiattoForm(Model model) {
		model.addAttribute("piatto", new Piatto());
		model.addAttribute("ingredienti", this.ingredienteService.findAll());
		return "piattoForm.html";
	}
	
	@GetMapping("/admin/deletePiatto/{id}")
	public String toDeletePiatto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("piatto", piattoService.findById(id));
		return "eliminaPiatto.html";
	}
	
	@PostMapping("/admin/deletePiatto/{id}")
	public String deletePiatto(@PathVariable("id") Long id, Model model) {
		String nextPage;
		try {
			this.piattoService.deleteById(id);
			model.addAttribute("piatti", piattoService.findAll());
			nextPage= "piatti.html";
		}
		catch (Exception e) {
			nextPage = "errorChiave.html";
		}
		model.addAttribute("piatti", piattoService.findAll());
		return nextPage;
	}
	
	
	@GetMapping("/admin/modificaPiattoForm/{id}")
	public String getPiattoForm(@PathVariable Long id, Model model) {
		model.addAttribute("piatto", piattoService.findById(id));
		model.addAttribute("ingredienti", ingredienteService.findAll());
		return "modificaPiattoForm.html";
	}
	
	@Transactional
	@PostMapping("/admin/modifica/piatto/{id}")
	public String modificaPiatto(@PathVariable Long id, @Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResults, Model model) {
		String nextPage;
		if(!bindingResults.hasErrors()) {
			Piatto vecchioPiatto = piattoService.findById(id);
			vecchioPiatto.setId(piatto.getId());
			vecchioPiatto.setNome(piatto.getNome());
			vecchioPiatto.setDescrizione(piatto.getDescrizione());
			this.piattoService.save(vecchioPiatto);
			model.addAttribute("piatto", piatto);
			nextPage = "piatto.html";
		} else {
			model.addAttribute("ingredienti", ingredienteService.findAll());
			nextPage = "modificaPiattoForm.html";
		}
		return nextPage;
	}
	
	
}
