package com.example.demo.personal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginAndRegisterController {

	@GetMapping("/registerPage")
	public String 회원가입페이지() {
		return "personal/registerPage";
	}
	
	@PostMapping("/registerPro")
	public String 회원가입() {
		return null;
	}
}
