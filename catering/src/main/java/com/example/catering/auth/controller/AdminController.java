package it.uniroma3.siw.siwcateringservice.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@GetMapping("/admin")
	//@IsStdUser
	public String getHomePage () {
		String nextPage = "admin_index.html";
		return nextPage;
	}

}