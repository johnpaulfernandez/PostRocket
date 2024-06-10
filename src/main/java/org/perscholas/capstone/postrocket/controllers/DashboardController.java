package org.perscholas.capstone.postrocket.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.capstone.postrocket.models.Request;
import org.perscholas.capstone.postrocket.models.User;
import org.perscholas.capstone.postrocket.models.UserInput;
import org.perscholas.capstone.postrocket.services.RequestService;
import org.perscholas.capstone.postrocket.services.RequestServiceImpl;
import org.perscholas.capstone.postrocket.services.UserService;
import org.perscholas.capstone.postrocket.services.UserServiceImpl;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class DashboardController {

    private final UserService userService;
    private final RequestService requestService;


    @Autowired
    private UserServiceImpl userServiceImpl;

    public DashboardController(UserService userService, RequestService requestService) {
        this.userService = userService;
        this.requestService = requestService;
    }

    @GetMapping("/dashboard")
    public String showDashboardPage(Model map) {
        map.addAttribute("user", userServiceImpl.getUser());
        List<Request> requests = requestService.getAllRequests();
        map.addAttribute("requests", requests);
        return "dashboard";
    }
}
