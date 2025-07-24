package com.recruit.controller.sample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recruit.router.Route;
import com.recruit.router.Router;
import com.recruit.router.ViewConstants;

@Controller
@RequestMapping("/popup")
public class PopupController {


	@GetMapping("/test")
	public String sample(Model model) {

		System.out.println("팝업창 테스트..!!");
		Route route  = Router.getInstance().getRoute("popup");
		model.addAttribute("route", route);
		return ViewConstants.INDEX;
	}

	
	@GetMapping("/popup-test")
	public String samplePop(Model model) {

		System.out.println("팝업창 테스트..!!");
		//Route  route  = Router.getInstance().getRoute("main");
		//model.addAttribute("route", route);
		return ViewConstants.POPUP_INDEX;
	}
	
	
}
