package com.handsonhip.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final RestTemplate restTemplate;
    private final String HUGGINGFACE_API_URL = "https://api-inference.huggingface.co/models/gpt2";

    @Value("${huggingface.api.token}")
    private String HUGGINGFACE_API_TOKEN;

    public AIController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateText(@RequestBody Map<String, String> request) {
        String prompt = request.get("prompt");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(HUGGINGFACE_API_TOKEN);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("inputs", prompt);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(HUGGINGFACE_API_URL, entity, String.class);

        return ResponseEntity.ok(response.getBody());
    }
}
