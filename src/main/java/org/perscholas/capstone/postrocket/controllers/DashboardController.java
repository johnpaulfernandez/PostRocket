package org.perscholas.capstone.postrocket.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.perscholas.capstone.postrocket.dto.UserDTO;
import org.perscholas.capstone.postrocket.models.GeneratedPost;
import org.perscholas.capstone.postrocket.models.Request;
import org.perscholas.capstone.postrocket.models.User;
import org.perscholas.capstone.postrocket.models.UserInput;
import org.perscholas.capstone.postrocket.services.*;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class DashboardController {

    private final RequestService requestService;

    private final GeneratedPostService postService;


    @Autowired
    private UserServiceImpl userServiceImpl;

    public DashboardController(RequestService requestService, GeneratedPostService postService) {
        this.requestService = requestService;
        this.postService = postService;
    }

    @GetMapping("/dashboard")
    public String showDashboardPage(ModelMap map) {
        return getDashboardData(map);
    }

    @PostMapping("/update/events/{postId}")
    public String updatePost(@PathVariable("postId") int postId, @ModelAttribute GeneratedPost post, ModelMap map)
    {
        try {
            GeneratedPost postToUpdate = postService.findGeneratedPostById(postId);

            if (post != null) {
                postToUpdate.setPost(post.getNewValue());
            }

            postService.saveGeneratedPost(postToUpdate);

            map.addAttribute("isSuccess", true);
        } catch (Exception e) {
            map.addAttribute("errorMessage", "Update failed!");
        }

        return getDashboardData(map);

    }

    private String getDashboardData(ModelMap map) {
        UserDTO userDTO = userServiceImpl.getUser();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = modelMapper.map(userDTO, User.class);
        map.addAttribute("user", user);

        List<Request> requests = requestService.getRequestsByUserId(userServiceImpl.getUserByEmail(user.getEmail()).getId());
        map.addAttribute("requests", requests);

        return "dashboard";
    }
}
