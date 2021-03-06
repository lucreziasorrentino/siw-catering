package com.example.catering.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.catering.auth.models.Credentials;
import com.example.catering.auth.service.CredentialsService;
import com.example.catering.service.ChefService;

import javax.validation.Valid;

@Controller
public class AuthController {

	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private ChefService chefService;

	@GetMapping("/")
	public String showIndex (Model model) {
		model.addAttribute("size", this.chefService.findAll().size());
		return "index";
	}
	
	@GetMapping("/login")
	public String showLoginForm (Model model) {
		return "loginForm";
	}

	@GetMapping("/logout")
	public String logout(Model model) {
		return "redirect:/public";
	}

	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("credentials", new Credentials());
		return "signUpForm";
	}

	@PostMapping("/register")
	public String register (@Valid @ModelAttribute("credentials") Credentials credentials, BindingResult bindingResult, Model model) {
		String nextPage;
		if (!bindingResult.hasErrors()) {
			//credentials.setRole("DEFAULT"); //decommentare se si vuole bloccare la registrazione di nuovi admin, chi si registra non potrà essere un admin
			this.credentialsService.saveCredentials(credentials);
			nextPage = "index.html";
		} else {
			model.addAttribute("credentials", credentials);
			nextPage = "signUpForm.html";
		}
		return nextPage;
	}

	@GetMapping("/default")
	public String defaultAfterLogin(Model model) {
		UserDetails adminDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(adminDetails.getUsername());
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			return "redirect:/admin";
		}
		return "redirect:/login";
	}
}
