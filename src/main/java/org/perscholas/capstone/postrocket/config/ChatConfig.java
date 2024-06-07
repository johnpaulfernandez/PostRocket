package org.perscholas.capstone.postrocket.config;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;


@Configuration
public class ChatConfig {
    @Bean
    public OpenAiApi openAiApi() {
        return new OpenAiApi(getApiKey());
    }

    @Bean
    public OpenAiImageApi openAiImageApi() {
        return new OpenAiImageApi(getApiKey());
    }

    private String getApiKey() {
        String apiKey = System.getenv("OPENAI_API_KEY");
        if (!StringUtils.hasText(apiKey)) {
            throw new IllegalArgumentException(
                    "You must provide an API key.  Put it in an environment variable under the name OPENAI_API_KEY");
        }
        return apiKey;
    }

    @Bean
    public OpenAiChatModel openAiChatModel(OpenAiApi api) {
        return new OpenAiChatModel(api);
    }

    @Bean
    public OpenAiImageModel openAiImageModel(OpenAiImageApi imageApi) {
        return new OpenAiImageModel(imageApi);
    }
}
