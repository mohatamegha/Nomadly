package com.example.Nomadly.controller;

import com.example.Nomadly.entities.ChatMessage;
import com.example.Nomadly.repository.ChatRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class MessageController {
    private final ChatRepo chatRepo;

    public MessageController(ChatRepo chatRepo) {
        this.chatRepo = chatRepo;
    }

    @GetMapping("/history/{id}")
    public List<ChatMessage> findAllMessages(@PathVariable Long id){
        List<ChatMessage> messages=chatRepo.findByTravel_TravelIdOrderByTimestampAsc(id);
        System.out.println(messages);
        return  messages;
    }
}
