package com.financascasal.api.controller;

import com.financascasal.api.domain.dto.UsuarioRequestDTO;
import com.financascasal.api.domain.model.Usuario;
import com.financascasal.api.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(usuarioRequestDTO.nome());
        novoUsuario.setEmail(usuarioRequestDTO.email());
        novoUsuario.setSenha(usuarioRequestDTO.senha());

        Usuario usuarioSalvo = usuarioService.salvar(novoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }
}
