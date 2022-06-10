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
import org.springframework.web.bind.annotation.RestController;

import com.example.catering.model.Buffet;
import com.example.catering.service.BuffetService;

@Controller
public class AmministratoreController {
	
	@RestController
	public class LoginController {

		@GetMapping("/perform_login")
		public String performLogin() {
			return "index.html";
		}

		@GetMapping("/perform_logout")
		public String performLogout() {
			return "index.html";
		}
	}	

}
