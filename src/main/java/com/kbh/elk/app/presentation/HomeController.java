package com.kbh.elk.app.presentation;

import com.kbh.elk.app.util.BaseEnum;
import com.kbh.elk.app.util.BookType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@GetMapping("/enumTest")
	public ResponseEntity enumToList(){
		return new ResponseEntity(BaseEnum.getEnumToMap(BookType.class), HttpStatus.OK);
	}
}
