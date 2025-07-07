package com.nadd.servicesense_api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadd.servicesense_api.AIService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ai")
public class AIController {
    @Autowired
    private AIService aiService;
    
    @PostMapping("/test")
    public ResponseEntity<String> testAI(@RequestBody String prompt) {
        String response = aiService.generateResponse(prompt);
        return ResponseEntity.ok(response);
    }
}