package com.recruit.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.recruit.router.Route;
import com.recruit.router.Router;
import com.recruit.router.ViewConstants;

@Controller
public class MainController {

	@GetMapping({"/", "/main"})
	public String main(Model model) {
		Route  route  = Router.getInstance().getRoute("main");
		model.addAttribute("route", route);
		
		return ViewConstants.INDEX;
	}	
}
