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

import com.example.catering.controller.validator.BuffetValidator;
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
	
	@Autowired
	private BuffetValidator buffetValidator;
	
	@PostMapping("/admin/buffetForm")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult, Model model) {
		this.buffetValidator.validate(buffet, bindingResult);
		if(!bindingResult.hasErrors()) {
			buffetService.save(buffet);
			model.addAttribute("buffet", buffet);
			return "buffet.html";
		}
		return "buffetForm.html";
	}
	
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

	@GetMapping("/admin/deleteBuffet/{id}")
	public String toDeleteBuffet(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", buffetService.findById(id));
		return "eliminaBuffet.html";
	}
	
	@PostMapping("/admin/deleteBuffet/{id}")
	public String deleteBuffet(@PathVariable("id") Long id, Model model) {
		buffetService.deleteById(id);
		model.addAttribute("listaBuffet", buffetService.findAll());
		return "listaBuffet.html";
	}
	
	@GetMapping("/admin/modificaBuffetForm/{id}")
	public String getBuffetForm(@PathVariable Long id, Model model) {
		model.addAttribute("buffet", buffetService.findById(id));
		model.addAttribute("listaChef", chefService.findAll());
		model.addAttribute("piatti", piattoService.findAll());
		return "modificaBuffetForm.html";
	}
	
	@Transactional
	@PostMapping("/admin/modifica/buffet/{id}")
	public String modificaBuffet (@PathVariable Long id, @Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResults, Model model) {
			String nextPage;
			if (!bindingResults.hasErrors()) {
				Buffet vecchioBuffet = buffetService.findById(id);
				vecchioBuffet.setId(buffet.getId());
				vecchioBuffet.setNome(buffet.getNome());
				vecchioBuffet.setDescrizione(buffet.getDescrizione());
				vecchioBuffet.setChef(buffet.getChef());
				vecchioBuffet.setPiatti(buffet.getPiatti());
				this.buffetService.save(vecchioBuffet);
				model.addAttribute("buffet", buffet);
				nextPage = "buffet.html";      // presenta un pagina con la buffet appena salvata
			} else {
				model.addAttribute("piatti", piattoService.findAll());
				model.addAttribute("listaChef", chefService.findAll());
				nextPage = "modificaBuffetForm.html"; // ci sono errori, torna alla form iniziale
		}
		return nextPage;
	}
	
	
}
