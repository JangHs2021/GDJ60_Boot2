package com.iu.base.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.iu.base.aoptest.Transport;
import com.iu.base.aoptest.card;

@Controller
public class HomeController {

	@Autowired
	private Transport transport;
	
	@Autowired
	private card card;
	
	@GetMapping(value = "/")
	public String home() {
		return "index";
	}
	
	@GetMapping(value = "/use")
	public void use() {
		
		transport.useBus();
		transport.useSubway();
		transport.takeWalk();
	}
}
