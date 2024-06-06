package org.perscholas.capstone.postrocket.controllers;

import org.perscholas.capstone.postrocket.models.User;
import org.perscholas.capstone.postrocket.services.UserService;
import org.perscholas.capstone.postrocket.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomePageController {

    private UserServiceImpl userDetailsService;

    @Autowired
    public HomePageController(UserServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/")
    private String redirectToHome()
    {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String showHomePage(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }
}
