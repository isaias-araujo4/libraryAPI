package br.com.araujo.libraryapi.autor.model.dto;

import java.time.LocalDate;

public record AutorDetailResponseDTO(
        Long id,
        String nome,

        //auditoria
        LocalDate dataCadastro,
        LocalDate dataAtualizacao,
        Long idUsuario
) {
}
