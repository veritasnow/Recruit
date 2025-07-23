package com.recruit.controller;

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
     * 유효성 검사 입력 폼 페이지 리턴
     */
    @GetMapping("/form")
    public String showValidationForm(Model model) {
        Route route = Router.getInstance().getRoute("validation");
        model.addAttribute("route", route);
        return ViewConstants.INDEX;
    }

}