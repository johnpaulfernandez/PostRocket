package org.perscholas.capstone.postrocket.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Controller
@SessionAttributes({"generatedPosts", "userInput"})
@Slf4j
public class EventsController {

    public static final String SUCCESS_URL = "/create/events";

    private final OpenAiChatModel chatModel;

    private final UserService userService;

    private final RequestService requestService;

    private final GeneratedPostService postService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RequestServiceImpl requestServiceImpl;


    @Autowired
    public EventsController(OpenAiChatModel chatModel, UserService userService, RequestService requestService, GeneratedPostService postService) {
        this.chatModel = chatModel;
        this.userService = userService;
        this.requestService = requestService;
        this.postService = postService;
    }

    @GetMapping("/create/events")
    public String showEventsPage(UserInput userInput, Model model) {
        model.addAttribute("user", userServiceImpl.getUser());
        model.addAttribute("userInput", userInput);
        return "events";
    }

    @PostMapping("/create/events")
    public String generateThread(UserInput userInput, ModelMap map) {
        BeanOutputConverter<List<GeneratedPost>> outputConverter = new BeanOutputConverter<>(new ParameterizedTypeReference<List<GeneratedPost>>() { });

        String format = outputConverter.getFormat();
        String template = """
				Generate 5 twitter posts for an event from this text - {text}.
				The first tweet (hook) should be a captivating opening sentence or question to grab attention.
				The subsequent tweets (body) should include some key points, specific details, or ask questions to encourage audience interaction.
				The last tweet should include a call to action (e.g., learn more, register for the event). Include hashtags and no emoticons.
				{format}
				""";

        PromptTemplate promptTemplate = new PromptTemplate(template, Map.of("text", userInput.getText(), "format", format));
        Prompt prompt = new Prompt(promptTemplate.createMessage());

        Generation generation = chatModel.call(prompt).getResult();
        List<GeneratedPost> generatedPosts = outputConverter.convert(generation.getOutput().getContent());

//        Request generatedPosts = null;

        map.addAttribute("user", userServiceImpl.getUser());
        map.addAttribute("userInput", userInput);
        map.addAttribute("generatedPosts", generatedPosts);

        return "events";
    }

    @PostMapping("/create/events/save")
    public String saveThread(UserInput userInput, ModelMap map)
    {
        UserDetails userDetails;

        Request request = new Request();

        request.setName(userInput.getTitle());

        try {
            userDetails = userService.loadUserByUsername(userServiceImpl.getUser().getEmail());
            log.info(userServiceImpl.getUser().getEmail());
        } catch (Exception e) {
            requestServiceImpl.setSuccessUrl(SUCCESS_URL);
            map.addAttribute("successUrl", SUCCESS_URL);
            return "signin";
        }

        if (userDetails != null) {
            request.setUser(userService.getUserByEmail(userServiceImpl.getUser().getEmail()));
        }

        requestService.saveRequest(request);

        ArrayList<GeneratedPost> generatedPosts = (ArrayList<GeneratedPost>) map.getAttribute("generatedPosts");

        if (generatedPosts != null) {
            for (GeneratedPost post : generatedPosts) {
                GeneratedPost newPost = new GeneratedPost();
                newPost.setRequest(request);
                newPost.setPost(post.getPost());

                postService.saveGeneratedPost(newPost);
            }
        }

        map.addAttribute("user", userServiceImpl.getUser());

        return "events";
    }

    @GetMapping("/dashboard")
    public String showDashboardPage(Model map, UserInput userInput) {

        UserDTO userDTO = userServiceImpl.getUser();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = modelMapper.map(userDTO, User.class);
        map.addAttribute("user", user);

        List<Request> requests = requestService.getRequestsByUserId(userServiceImpl.getUserByEmail(user.getEmail()).getId());
        map.addAttribute("requests", requests);

        map.addAttribute("userInput", userInput);
        return "dashboard";
    }

    @PostMapping("/update/events/{postId}")
    public String updatePost(@PathVariable("postId") int postId, ModelMap map, @ModelAttribute GeneratedPost post)
    {
        GeneratedPost postToUpdate = postService.findGeneratedPostById(postId);

        if (post != null) {
            postToUpdate.setPost(post.getNewValue());
        }

        postService.saveGeneratedPost(postToUpdate);

        return "dashboard";
    }
}