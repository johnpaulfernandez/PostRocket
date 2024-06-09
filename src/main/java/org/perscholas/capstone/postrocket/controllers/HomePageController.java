package org.perscholas.capstone.postrocket.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.capstone.postrocket.dto.UserDTO;
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
@Slf4j
public class HomePageController {

    private UserServiceImpl userDetailsService;

    @Autowired
    public HomePageController(UserServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/")
    private String redirectToHome() {

        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String showHomePage(UserDTO user, Model model) {
        model.addAttribute("user", user.getEmail());
        userServiceImpl.setUser(user);
        log.info(user.getEmail());
        return "home";
    }
}
