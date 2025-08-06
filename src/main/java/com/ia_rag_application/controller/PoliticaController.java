package com.ia_rag_application.controller;

import com.ia_rag_application.model.PoliticaEmpresa;
import com.ia_rag_application.service.SavePoliticaUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/politica")
public class PoliticaController {

    private final SavePoliticaUseCase savePoliticaUseCase;

    public PoliticaController(SavePoliticaUseCase savePoliticaUseCase) {
        this.savePoliticaUseCase = savePoliticaUseCase;
    }

    @PostMapping("/salvar")
    public ResponseEntity<Void> salvarPolitica(@RequestBody PoliticaEmpresa politicaEmpresa) {
        savePoliticaUseCase.salvarPolitica(politicaEmpresa);
        return ResponseEntity.ok().build();
    }

}
