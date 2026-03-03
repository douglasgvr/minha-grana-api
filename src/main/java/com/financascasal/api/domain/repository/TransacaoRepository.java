package com.financascasal.api.domain.repository;

import com.financascasal.api.domain.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {

    List<Transacao> findByContaCasalIdOrderByDataPagamentoDesc(UUID contaCasalId);
}
