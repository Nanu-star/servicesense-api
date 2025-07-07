package com.nadd.servicesense_api;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.api.OpenAiApi.ChatModel;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    
    private final ChatModel chatModel;
    
    public AIService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }
    
    public String generateResponse(String prompt) {
        try {
            ChatResponse response = chatModel.call(new Prompt(prompt));
            if (response == null || response.getResult() == null || response.getResult().getOutput() == null || response.getResult().getOutput().getContent() == null) {
                return "No response generated.";
            }
            return response.getResult().getOutput().getContent();
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