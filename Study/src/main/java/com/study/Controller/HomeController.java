package com.study.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {	
	
	//홈	
	@RequestMapping(value = "/")
	public String home(Model model) {
		
		System.out.println("로그인컨트롤러.......");		
		
		return "LoginTest";
		
	}

}
