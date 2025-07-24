package com.recruit.controller.sample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recruit.router.Route;
import com.recruit.router.Router;
import com.recruit.router.ViewConstants;

@Controller
@RequestMapping("/security")
public class SecurityController {

    /**
     * 보안관련
     */
    @GetMapping("/form")
    public String showValidationForm(Model model) {
        Route route = Router.getInstance().getRoute("security");
        model.addAttribute("route", route);
        return ViewConstants.INDEX;
    }

}