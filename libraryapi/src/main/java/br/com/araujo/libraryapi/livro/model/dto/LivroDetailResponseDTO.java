package br.com.araujo.libraryapi.livro.model.dto;

import br.com.araujo.libraryapi.autor.model.dto.AutorResponseDTO;
import br.com.araujo.libraryapi.livro.model.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LivroDetailResponseDTO(
        Long id,
        String titulo,

        // auditoria
        LocalDate dataCadastro,
        LocalDate dataAtualizacao,
        Long idUsuario
) {
}
