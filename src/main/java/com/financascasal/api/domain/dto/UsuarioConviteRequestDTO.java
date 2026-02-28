package com.financascasal.api.domain.dto;

import java.util.UUID;

public record UsuarioConviteRequestDTO(String nome, String email, String senha, UUID contaCasalId) {
}
