package com.ia_rag_application.service;

import com.ia_rag_application.model.PoliticaEmpresa;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PoliticaMapper {

    public static Document toDocument(PoliticaEmpresa politica) {
        Map<String, Object> metadata = Map.of(
                "id", politica.getId().toString(),
                "categoria", politica.getCategoria(),
                "titulo", politica.getTitulo()
        );

        return new Document(
                politica.getId().toString(),
                politica.getConteudo(),
                metadata
        );
    }

}
