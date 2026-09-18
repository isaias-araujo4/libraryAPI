package br.com.araujo.libraryapi.usuario.model.dto;

import java.util.List;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        List<String> roles
) {
}
