package com.ia_rag_application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.document.Document;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ChatService {

    private final VectorStore vectorStore;
    private final OllamaChatModel ollamaChatModel;

    public ChatService(VectorStore vectorStore, OllamaChatModel ollamaChatModel) {
        this.vectorStore = vectorStore;
        this.ollamaChatModel = ollamaChatModel;
    }

    public String chat(String query) {
        var content =  ChatClient.builder(ollamaChatModel)
                .build().prompt()
                .advisors(new QuestionAnswerAdvisor(vectorStore))
                .user(query)
                .call()
                .content();
        log.info("Chat response: {}", content);
        return content;
    }

    public String chatRagTwo(String query) {
        var search = SearchRequest
                .builder()
                .topK(3)
                .query(query)
                .build();
        var docs = vectorStore.similaritySearch(search);
        var prompt = createPrompt(Objects.requireNonNull(docs), query);
        log.info("Context for RAG: {}", prompt);
        return ChatClient.builder(ollamaChatModel)
                .build().prompt()
                .user(prompt)
                .call()
                .content();
    }

    public String createPrompt(List<Document> docs, String query){
        StringBuilder context = new StringBuilder();
        context.append("Query: ").append(query).append("\n");
        context.append("Context: \n");
        for (Document doc : docs) {
            context.append("- ").append(doc.getText()).append("\n");
        }
        log.info("Context retrieved from documents: {}", context);
        return context.toString();
    }
}
