package com.recruit.controller.sample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recruit.router.Route;
import com.recruit.router.Router;
import com.recruit.router.ViewConstants;

@Controller
@RequestMapping("/pdf")
public class PdfController {

	@GetMapping("/test")
	public String sample(Model model) {

		System.out.println("테스트..!!");
		
		Route  route  = Router.getInstance().getRoute("pdf");
		model.addAttribute("route", route);
		
		System.out.println("ViewConstants.INDEX : " + ViewConstants.INDEX);
		System.out.println("route : " + route.toString());
		
		return ViewConstants.INDEX;
	}	

	

	@GetMapping("/popup")
	public String popup(Model model) {
		System.out.println("pdf저장 테스트..!!");
		return "/sample/pdf/pdf_image";
	}		
	
	
}
