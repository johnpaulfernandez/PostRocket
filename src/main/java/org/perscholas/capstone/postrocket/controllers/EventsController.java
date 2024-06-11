package org.perscholas.capstone.postrocket.controllers;

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
@SessionAttributes({"requestOutput", "userInput"})
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
        model.addAttribute("user", userServiceImpl.getUser());
        return "events";
    }

    @PostMapping("/create/events")
    public String generateThread(UserInput userInput, ModelMap map) {
        BeanOutputConverter<Request> outputConverter = new BeanOutputConverter<>(Request.class);

        String format = outputConverter.getFormat();
        String template = """
				Generate 5 twitter posts for an event from this text - {text}.
				The first tweet (hook) should be a captivating opening sentence or question to grab attention.
				The subsequent tweets (body) should include some key points, specific details, or ask questions to encourage audience interaction.
				The last tweet should include a call to action (e.g., learn more, register for the event).
				Include hashtags.
				{format}
				""";

        PromptTemplate promptTemplate = new PromptTemplate(template, Map.of("text", userInput.getText(), "format", format));
        Prompt prompt = new Prompt(promptTemplate.createMessage());

        Generation generation = chatModel.call(prompt).getResult();
        Request requestOutput = new Request();

        requestOutput = outputConverter.convert(generation.getOutput().getContent());

//        Request requestOutput = null;

        map.addAttribute("user", userServiceImpl.getUser());
        map.addAttribute("userInput", userInput);
        map.addAttribute("requestOutput", requestOutput);

        return "events";
    }

    @PostMapping("/create/events/save")
    public String saveThread(UserInput userInput, ModelMap map)
    {
        UserDetails userDetails;

        Request request = new Request();

        request.setName(userInput.getTitle());

//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        User user = modelMapper.map(userServiceImpl.getUser(), User.class);


        try {
            userDetails = userService.loadUserByUsername(userServiceImpl.getUser().getEmail());
            log.info(userServiceImpl.getUser().getEmail());
        } catch (Exception e) {
//            request.setUser(user);

            requestServiceImpl.setSuccessUrl(SUCCESS_URL);
            map.addAttribute("successUrl", SUCCESS_URL);
            return "signin";
        }

        if (userDetails != null) {
            request.setUser(userService.getUserByEmail(userServiceImpl.getUser().getEmail()));
        }

        requestService.saveRequest(request);

        Request requestAttribute = (Request) map.getAttribute("requestOutput");

        if (requestAttribute != null) {

            for (GeneratedPost post : requestAttribute.getPosts()) {
                GeneratedPost newPost = new GeneratedPost();
                newPost.setRequest(request);
                newPost.setPost(post.getPost());

                postService.saveGeneratedPost(newPost);
            }
        }

        map.addAttribute("user", userServiceImpl.getUser());

        return "events";
    }

    @PostMapping("/update/events/{postId}")
    public void updatePost(@PathVariable("postId") int postId)
    {
        GeneratedPost post = postService.findGeneratedPostById(postId);
        postService.saveGeneratedPost(post);
    }
}
