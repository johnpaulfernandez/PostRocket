package org.perscholas.capstone.postrocket.controllers;

import org.perscholas.capstone.postrocket.models.GeneratedPost;
import org.perscholas.capstone.postrocket.models.Request;
import org.perscholas.capstone.postrocket.models.User;
import org.perscholas.capstone.postrocket.models.UserInput;
import org.perscholas.capstone.postrocket.services.UserServiceImpl;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class EventsController {

    private final OpenAiChatModel chatModel;

    record OutputRecord(List<String> output) {
    }

    @Autowired
    public EventsController(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/create/events")
    public String showEventsPage(@ModelAttribute UserInput userInput, Model model) {
        model.addAttribute("userInput", userInput);
        return "events";
    }

    @PostMapping("/create/events")
    public String generateThread(@ModelAttribute UserInput userInput, @ModelAttribute Request output, ModelMap map) {
        BeanOutputConverter<Request> outputConverter = new BeanOutputConverter<>(Request.class);

        String format = outputConverter.getFormat();
        String template = """
				Generate 5 twitter posts from this text - {text}.
				{format}
				""";

        PromptTemplate promptTemplate = new PromptTemplate(template, Map.of("text", userInput.getText(), "format", format));
        Prompt prompt = new Prompt(promptTemplate.createMessage());
        Generation generation = chatModel.call(prompt).getResult();

        Request requestOutput = outputConverter.convert(generation.getOutput().getContent());
        map.addAttribute("userInput", userInput);
        map.addAttribute("requestOutput", requestOutput);

        return "events";
    }
}
