package com.insta.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	 //redirect to demo if user hits the root
    @RequestMapping("/")
    public String home(Model model) {
        return "redirect:/insta/Login.html";

    }
}
