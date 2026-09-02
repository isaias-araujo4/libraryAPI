package br.com.araujo.libraryapi.livro.model.dto;

import br.com.araujo.libraryapi.autor.model.DTO.AutorDTO;
import br.com.araujo.libraryapi.livro.model.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ResultadoPesquisaLivroDTO(
        Long id,
        String titulo,
        LocalDate dataPublicacao,
        GeneroLivro genero,
        BigDecimal preco,
        AutorDTO autor
) {
}
