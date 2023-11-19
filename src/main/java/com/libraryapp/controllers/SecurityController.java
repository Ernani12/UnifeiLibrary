package com.libraryapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.libraryapp.entities.Usuario;
import com.libraryapp.services.UsuarioService;

@Controller
public class SecurityController {
	
	@Autowired
	BCryptPasswordEncoder pwEncoder;
	
	@Autowired
	UsuarioService accService;
	
	@GetMapping(value="/login")
	public String login() {
		return "security/login.html";
	}
	
	@GetMapping(value="/logout")
	public String logout() {
		return "security/logout.html";
	}
	
	@GetMapping(value="/register")
	public String register(Model model) {
		model.addAttribute("newAccount", new Usuario());
		return "security/register.html";
	}
	//criada a conta usuario
	@PostMapping(value="/register/save")
	public String saveNewAccount(Usuario account) {
		account.setPassword(pwEncoder.encode(account.getPassword()));
		accService.save(account);
		return "redirect:/register/accountcreated";
	}
	//
	@GetMapping(value="/register/accountcreated")
	public String accountCreated() {
		return "security/account-created.html";
	}	
}
