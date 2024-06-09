package org.perscholas.capstone.postrocket.controllers;

import jakarta.validation.Valid;
import org.perscholas.capstone.postrocket.dto.UserDTO;
import org.perscholas.capstone.postrocket.models.GeneratedPost;
import org.perscholas.capstone.postrocket.models.Request;
import org.perscholas.capstone.postrocket.models.User;
import org.perscholas.capstone.postrocket.models.UserInput;
import org.perscholas.capstone.postrocket.services.RequestService;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("requestOutput")
public class EventsController {

    private final OpenAiChatModel chatModel;

    private final UserService userService;

    private final RequestService requestService;

    @Autowired
    private UserServiceImpl userServiceImpl;


    record OutputRecord(List<String> output) {
    }

    @Autowired
    public EventsController(OpenAiChatModel chatModel, UserService userService, RequestService requestService) {
        this.chatModel = chatModel;
        this.userService = userService;
        this.requestService = requestService;
    }

    @GetMapping("/create/events")
    public String showEventsPage(@ModelAttribute UserInput userInput, Model model) {
        model.addAttribute("userInput", userInput);
        model.addAttribute("user", userServiceImpl.getUser());
        return "events";
    }

    @PostMapping("/create/events")
    public String generateThread(@ModelAttribute UserInput userInput, ModelMap map) {
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
        Request requestOutput = outputConverter.convert(generation.getOutput().getContent());

//        Request requestOutput = null;

        map.addAttribute("userInput", userInput);
        map.addAttribute("requestOutput", requestOutput);

        return "events";
    }

    /*
    Get user from session attribute
     */
    @PostMapping("/create/events/save")
    public String saveThread(@ModelAttribute UserInput userInput, ModelMap map)
    {
        Request request = (Request) map.getAttribute("requestOutput");

        UserDetails userDetails = userService.loadUserByUsername(userServiceImpl.getUser().getEmail());

        if (userDetails != null) {
            request.setUser(userService.getUserByEmail(userServiceImpl.getUser().getEmail()));
        }

        map.addAttribute("userInput", userInput);
        requestService.saveRequest(request);

        return "events";
    }
}
