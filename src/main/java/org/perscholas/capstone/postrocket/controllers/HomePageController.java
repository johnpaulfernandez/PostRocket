package org.perscholas.capstone.postrocket.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.capstone.postrocket.models.User;
import org.perscholas.capstone.postrocket.services.UserService;
import org.perscholas.capstone.postrocket.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("user")
@Slf4j
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
    public String showHomePage(User user, ModelMap map) {
        if (map.getAttribute("user") == null) {
            map.addAttribute("user", new User());
        } else {
            userDetailsService.loadUserByUsername(user.getEmail());
            log.info(user.getEmail());
        }
        return "home";
    }
}
