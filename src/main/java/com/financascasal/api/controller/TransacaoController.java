package com.financascasal.api.controller;

import com.financascasal.api.domain.dto.TransacaoRequestDTO;
import com.financascasal.api.domain.model.Transacao;
import com.financascasal.api.domain.service.TrasicaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transacoes")
@RequiredArgsConstructor
public class TransacaoController {

    private final TrasicaoService trasicaoService;

    @PostMapping
    public ResponseEntity<Transacao> registrar(@RequestBody TransacaoRequestDTO transacaoRequestDTO){
        Transacao novaTransacao = new Transacao();
        novaTransacao.setDescricao(transacaoRequestDTO.descricao());
        novaTransacao.setValor(transacaoRequestDTO.valor());
        novaTransacao.setDataPagamento(transacaoRequestDTO.dataPagamento());
        novaTransacao.setTipo(transacaoRequestDTO.tipoTransacao());
        novaTransacao.setDespesaConjunta(transacaoRequestDTO.despesaConjunta());

        Transacao transacaoSalva = trasicaoService.registrar(
                novaTransacao,
                transacaoRequestDTO.usuarioId(),
                transacaoRequestDTO.contaCasalId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoSalva);
    }
}
