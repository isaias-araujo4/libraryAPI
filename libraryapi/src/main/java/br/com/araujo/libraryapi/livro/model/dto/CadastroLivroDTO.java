package br.com.araujo.libraryapi.livro.model.dto;

import br.com.araujo.libraryapi.livro.model.GeneroLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CadastroLivroDTO (
        @NotBlank(message = "campo é obrigatorio")
        String titulo,

        @NotBlank(message = "campo é obrigatorio")
        @Past(message = "não pode ser uma data futura")
        LocalDate dataPublicacao,

        @NotBlank(message = "campo é obrigatorio")
        GeneroLivro genero,

        @NotBlank(message = "campo é obrigatorio")
        BigDecimal preco,

        @NotBlank(message = "campo é obrigatorio")
        Long idAutor
){
}
