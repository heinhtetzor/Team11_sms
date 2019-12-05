package com.team11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/your")
public class YourController {
	
	@GetMapping("/hello/{message}")
	public String yourMethod(@PathVariable("message") String m, Model model) {
		System.out.println("Message"+m);
		model.addAttribute("name", m);
		return "welcome";
	}
	
	@GetMapping("/controller1")
	public String controller1() {
		System.out.println("This is in controller 1");
		return "redirect:/your/controller2";
	}
	
	@GetMapping("/controller2")
	public String controller2() {
		System.out.println("This is in controller 2");
		return "test";
	}
	

}
