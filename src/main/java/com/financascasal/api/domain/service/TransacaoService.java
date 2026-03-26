package com.financascasal.api.domain.service;

import com.financascasal.api.domain.dto.ResumoContaDTO;
import com.financascasal.api.domain.model.ContaCasal;
import com.financascasal.api.domain.model.Transacao;
import com.financascasal.api.domain.model.Usuario;
import com.financascasal.api.domain.repository.ContaCasalRepository;
import com.financascasal.api.domain.repository.TransacaoRepository;
import com.financascasal.api.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    private final UsuarioRepository usuarioRepository;
    private final ContaCasalRepository contaCasalRepository;

    @Transactional
    public Transacao registrar(Transacao transacao, UUID usuarioId, UUID contaCasalId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException("Erro: Usuário não encontrado"));

        ContaCasal contaCasal = contaCasalRepository.findById(contaCasalId).orElseThrow(() -> new IllegalArgumentException("Erro: Conta Casal não encontrada"));

        if (usuario.getContaCasal() == null || !usuario.getContaCasal().getId().equals(contaCasal.getId())) {
            throw new IllegalArgumentException("Bloqueado: Usuário não pertence a esta Conta Casal");
        }

        transacao.setUsuarioResponsavel(usuario);
        transacao.setContaCasal(contaCasal);

        return transacaoRepository.save(transacao);
    }

    public List<Transacao> buscarExtratoDaConta(UUID contaCasalId) {
        return transacaoRepository.findByContaCasalIdOrderByDataPagamentoDesc(contaCasalId);
    }

    public ResumoContaDTO obterResumoDaConta(UUID contaCasalId) {
        List<Transacao> transacoes = buscarExtratoDaConta(contaCasalId);

        BigDecimal receitas = BigDecimal.ZERO;
        BigDecimal despesas = BigDecimal.ZERO;

        for (Transacao transacao : transacoes) {
            if(transacao.getTipo().name().equals("RECEITA")) {
                receitas = receitas.add(transacao.getValor());
            }else {
                despesas = despesas.add(transacao.getValor());
            }
        }

        BigDecimal saldo = receitas.subtract(despesas);

        return new ResumoContaDTO(receitas, despesas, saldo);
    }
}
