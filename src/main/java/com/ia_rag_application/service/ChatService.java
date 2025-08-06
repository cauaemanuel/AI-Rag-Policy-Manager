package com.ia_rag_application.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final VectorStore vectorStore;
    private final OllamaChatModel ollamaChatModel;

    public ChatService(VectorStore vectorStore, OllamaChatModel ollamaChatModel) {
        this.vectorStore = vectorStore;
        this.ollamaChatModel = ollamaChatModel;
    }

    public String chat(String query) {
        return ChatClient.builder(ollamaChatModel)
                .build().prompt()
                .advisors(new QuestionAnswerAdvisor(vectorStore))
                .user(query)
                .call()
                .content();
    }
}
