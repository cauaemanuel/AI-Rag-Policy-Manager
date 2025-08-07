package com.ia_rag_application.controller;

import com.ia_rag_application.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@Slf4j
public class QuestionController {

    private final ChatService chatService;

    public QuestionController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public ResponseEntity<String> salvarPolitica(@RequestBody String query) {
        log.info("Received query: {}", query);
        return ResponseEntity.ok(chatService.chatRagTwo(query));
    }

}
