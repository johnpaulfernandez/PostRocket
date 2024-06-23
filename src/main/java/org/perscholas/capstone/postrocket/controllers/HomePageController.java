package org.perscholas.capstone.postrocket.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.capstone.postrocket.models.User;
import org.perscholas.capstone.postrocket.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class HomePageController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/")
    private String redirectToHome() {

        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String showHomePage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if(userDetails != null) {
            String username = userDetails.getUsername();
            User user = userServiceImpl.getUserByEmail(username);
            model.addAttribute("user", user);
        }
        return "home";
    }
}
