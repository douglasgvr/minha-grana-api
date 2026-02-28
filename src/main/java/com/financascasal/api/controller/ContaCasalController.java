package com.financascasal.api.controller;

import com.financascasal.api.domain.dto.ContaCasalRequestDTO;
import com.financascasal.api.domain.model.ContaCasal;
import com.financascasal.api.domain.service.ContaCasalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/contas")
@RequiredArgsConstructor
public class ContaCasalController {

    private final ContaCasalService contaCasalService;

    @PostMapping
    public ResponseEntity<ContaCasal> criarConta(@RequestBody ContaCasalRequestDTO contaCasalRequestDTO) {
        ContaCasal novaConta = new ContaCasal();
        novaConta.setNome(contaCasalRequestDTO.nome());

        ContaCasal contaSalva = contaCasalService.criar(novaConta, contaCasalRequestDTO.usuarioId());

        return ResponseEntity.status(HttpStatus.CREATED).body(contaSalva);
    }
}
