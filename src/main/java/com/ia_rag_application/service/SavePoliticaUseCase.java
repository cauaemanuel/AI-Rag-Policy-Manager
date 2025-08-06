package com.ia_rag_application.service;

import com.ia_rag_application.model.PoliticaEmpresa;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Configuration
public class SavePoliticaUseCase {

    private final VectorStore vectorStore;

    public SavePoliticaUseCase(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void salvarPolitica(PoliticaEmpresa politicaEmpresa) {
        var doc = PoliticaMapper.toDocument(politicaEmpresa);
        vectorStore.accept(List.of(doc));
    }

    @PostConstruct
    public void deletePolitica() {
        // Salvar duas políticas
        PoliticaEmpresa politica1 = new PoliticaEmpresa(
                UUID.randomUUID(),
                "Politica A",
                "Categoria 1",
                "Conteúdo da Política A",
                LocalDate.now()
        );
        PoliticaEmpresa politica2 = new PoliticaEmpresa(
                UUID.randomUUID(),
                "Politica B",
                "Categoria 2",
                "Conteúdo da Política B",
                LocalDate.now()
        );
        salvarPolitica(politica1);
        salvarPolitica(politica2);

// Pesquisar uma política pelo nome
        List<Document> docs = vectorStore.similaritySearch("Politica A");
        if (!docs.isEmpty()) {
            Document docEncontrado = docs.get(0);

            System.out.println(docs);
            vectorStore.delete(docs.stream().map(p -> p.getId()).toList());
        }
    }
}
