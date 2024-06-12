package org.perscholas.capstone.postrocket.controllers;

import org.perscholas.capstone.postrocket.services.RequestServiceImpl;
import org.perscholas.capstone.postrocket.services.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.capstone.postrocket.dto.UserDTO;
import org.perscholas.capstone.postrocket.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@SessionAttributes("user")
/*
This controller is mapped to “/sign-up” URI.
We use the UserDto to process and validate the user registration form and inject it using the @ModelAttribute("user") (Data Transfer Object) annotation.
When the form is submitted it’s automatically validated and errors are available in the BindingResult.
If the form has any errors, we return to the registration page.
Otherwise, we redirect to the sign-in page.
 */
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RequestServiceImpl requestServiceImpl;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    /*
    When the controller is accessed for the first time, Spring instantiates an instance
    and places the session attribute 'user' in the Model.
     */
    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

    private UserServiceImpl userDetailsService;

    @Autowired
    public UserController(UserServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(ModelMap map) {
        map.addAttribute("user", new UserDTO());
        return "register";
    }

    @GetMapping("/signin")
    public String showSignInForm(ModelMap map) {
        map.addAttribute("successUrl", requestServiceImpl.getSuccessUrl());
        return "signin";
    }

    @PostMapping("/registration-process")
    public String registrationProcess(@Valid @ModelAttribute ("user") UserDTO user, BindingResult bindingResult, ModelMap map)
    {
        if(bindingResult.hasErrors())
        {
            log.warn("Failed registration attempt!");
            return "register";
        }
        userDetailsService.create(user);

        return "redirect:/signin";
    }
}
