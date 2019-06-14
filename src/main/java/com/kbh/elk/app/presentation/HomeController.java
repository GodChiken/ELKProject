package com.kbh.elk.app.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@GetMapping("/hm")
	public ModelAndView homeByModelAncView(){
		return new ModelAndView("home");
	}
	@GetMapping("/hs")
	public String homeByString() {
		return "home";
	}
}
