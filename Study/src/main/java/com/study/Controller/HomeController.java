package com.study.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {	
	
	//Ȩ	
	@RequestMapping(value = "/")
	public String home(Model model) {
		
		System.out.println("�α�����Ʈ�ѷ�.......");		
		
		return "LoginTest";
		
	}

}
