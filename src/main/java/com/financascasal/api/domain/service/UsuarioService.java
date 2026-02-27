package com.financascasal.api.domain.service;

import com.financascasal.api.domain.model.Usuario;
import com.financascasal.api.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {

        boolean emailEmUso = usuarioRepository.existsByEmail(usuario.getEmail());
        if (emailEmUso) {
            throw new IllegalArgumentException("Já existe um usuário com este e-mail cadastrado");
        }

        return usuarioRepository.save(usuario);
    }
}
