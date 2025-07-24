package com.recruit.controller.sample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recruit.router.Route;
import com.recruit.router.Router;
import com.recruit.router.ViewConstants;

@Controller
@RequestMapping("/validation")
public class ValidationController {

    /**
     * 유효성 검사 입력 폼 페이지
     */
    @GetMapping("/sample")
    public String sample(Model model) {
    	
    	System.out.println("sample 입력폼페이지 접근...!!");
    	
        Route route = Router.getInstance().getRoute("validation");
        model.addAttribute("route", route);
        return ViewConstants.IFRAME_INDEX;
    }
}