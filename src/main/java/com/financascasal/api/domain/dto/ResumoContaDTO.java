package com.financascasal.api.domain.dto;

import java.math.BigDecimal;

public record ResumoContaDTO (BigDecimal totalReceitas,
                              BigDecimal totalDespesas,
                              BigDecimal saldoAtual) {


}
