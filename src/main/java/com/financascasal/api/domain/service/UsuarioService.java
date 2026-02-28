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
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final ContaCasalRepository contaCasalRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {

        boolean emailEmUso = usuarioRepository.existsByEmail(usuario.getEmail());
        if (emailEmUso) {
            throw new IllegalArgumentException("Já existe um usuário com este e-mail cadastrado");
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario cadastrarConvidado(Usuario usuario, UUID contaCasalId) {
        validarEmail(usuario.getEmail());

        ContaCasal contaCasal = contaCasalRepository.findById(contaCasalId).orElseThrow(() -> new IllegalArgumentException("Erro: Conta Casal não encontrada"));

        usuario.setContaCasal(contaCasal);
        return usuarioRepository.save(usuario);
    }

    private void validarEmail(String email){
        if(usuarioRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Já existe um usuário com este e-mail cadastrado");
        }
    }
}
