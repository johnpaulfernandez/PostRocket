package org.perscholas.capstone.postrocket.controllers;

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
public class UserController {

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
    public UserDTO setUpUserForm() {
        return new UserDTO();
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
    public String showSignInForm() {
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

    @GetMapping("/admin/all-users")
    public String getAllUsers(Model model) {
        List<User> users = userDetailsService.getAllUsers();
//        model.addAttribute("users", users);
        return "all-users";
    }

    @GetMapping("/admin/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        User user = userDetailsService.getUserById(id);
//        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/admin/edit/{id}")
    public String editUser(@PathVariable("id") int id, @ModelAttribute("user") User user) {
        user.setId(id);
        userDetailsService.updateUser(user);
        return "redirect:/admin/all-users";
    }

    @DeleteMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userDetailsService.deleteUser(id);
        return "redirect:/admin/all-users";
    }
}
