package com.futbol.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping({"/inicio"})
	public String Inicio(Model model) {
		model.addAttribute("titulo","Bienvenido");
		return"index";
	}
	
	@GetMapping("/login")
	public String Login() {
		return"login";
	}
}
