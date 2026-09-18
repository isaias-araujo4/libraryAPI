package br.com.araujo.libraryapi.autor.model.dto;

import br.com.araujo.libraryapi.livro.model.dto.LivroResponseAutorDTO;

import java.time.LocalDate;

public record AutorResponseDTO(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String nacionalidade,
        LivroResponseAutorDTO livro
) {
}
