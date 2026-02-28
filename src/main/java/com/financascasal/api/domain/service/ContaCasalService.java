package com.financascasal.api.domain.service;

import com.financascasal.api.domain.model.ContaCasal;
import com.financascasal.api.domain.model.Usuario;
import com.financascasal.api.domain.repository.ContaCasalRepository;
import com.financascasal.api.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContaCasalService {

    private final ContaCasalRepository contaCasalRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public ContaCasal criar(ContaCasal contaCasal, UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException("Erro: Usuário não encontrado"));

        ContaCasal contaSalva = contaCasalRepository.save(contaCasal);

        usuario.setContaCasal(contaSalva);

        usuarioRepository.save(usuario);

        return contaSalva;
    }
}
