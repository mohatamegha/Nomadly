package com.example.Nomadly.controller;

import com.example.Nomadly.entities.ChatMessage;
import com.example.Nomadly.entities.ChatMessageWrapper;
import com.example.Nomadly.entities.Travel;
import com.example.Nomadly.entities.User;
import com.example.Nomadly.repository.ChatRepo;
import com.example.Nomadly.repository.TravelRepo;
import com.example.Nomadly.repository.UserRepo;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRepo chatRepo;
    private final UserRepo userRepo;
    private final TravelRepo travelRepo;
    public ChatController(SimpMessagingTemplate messagingTemplate, ChatRepo chatRepo, UserRepo userRepo, TravelRepo travelRepo) {
        this.messagingTemplate = messagingTemplate;
        this.chatRepo = chatRepo;
        this.userRepo = userRepo;
        this.travelRepo = travelRepo;
    }
    @MessageMapping("/chat.send")
    public void send(ChatMessageWrapper message){
        System.out.println("reached");
        User user=userRepo.findById(message.getUserId()).orElseThrow(() -> new RuntimeException());
       Travel travel=travelRepo.findById(message.getTravelId()).orElseThrow(() -> new RuntimeException());;
    ChatMessage msg=new ChatMessage();
    msg.setId(message.getId());
    msg.setContent(message.getContent());
    msg.setUser(user);
    msg.setTravel(travel);
    msg.setTimestamp(LocalDateTime.now());
    chatRepo.save(msg);

    messagingTemplate.convertAndSend(
                "/topic/chat/" + message.getTravelId(),
                msg
        );

    }

}
