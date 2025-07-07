package com.nadd.servicesense_api.config;

import org.springframework.ai.model.openai.autoconfigure.OpenAiChatProperties;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiApi.ChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfig {
    @Value("${openai.api-key:}")
    private String openaiApiKey;

    public String getOpenaiApiKey() {
        return openaiApiKey;
    }
     @Bean
    public OpenAiApi openAIClient(OpenAiChatProperties properties) {
        return new OpenAIClient(properties.getApiKey());
    }

    @Bean
    public ChatModel chatModel(OpenAiApi client) {
        return new OpenAIChatModel(client);
    }
}
