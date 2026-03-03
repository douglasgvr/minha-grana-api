package com.financascasal.api.domain.dto;

import com.financascasal.api.domain.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransacaoRequestDTO(String descricao, BigDecimal valor, LocalDate dataPagamento, TipoTransacao tipoTransacao, boolean despesaConjunta, UUID usuarioId, UUID contaCasalId) {
}
