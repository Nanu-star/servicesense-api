package com.nadd.servicesense_api;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    
    private final ChatClient chatClient;
    
    public AIService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }
    
    public String generateResponse(String prompt) {
        try {
            return chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();
        } catch (Exception e) {
            // Optionally, use a logger here
            return "Error generating response: " + e.getMessage();
        }
    }
    
    public String analyzeCode(String code) {
        String prompt = "Analyze this code and provide a brief summary:\n"
                + "\n"
                + "```\n"
                + code + "\n"
                + "```\n"
                + "\n"
                + "Please provide:\n"
                + "1. Technology stack detected\n"
                + "2. Main functionality\n"
                + "3. Potential issues";
        
        return generateResponse(prompt);
    }
}