package com.ia_rag_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliticaEmpresa {

    private UUID id;
    private String titulo;
    private String categoria;
    private String conteudo;
    private LocalDate data;
}
