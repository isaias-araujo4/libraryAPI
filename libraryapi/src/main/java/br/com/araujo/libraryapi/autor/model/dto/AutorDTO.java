package br.com.araujo.libraryapi.autor.model.dto;

import br.com.araujo.libraryapi.autor.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record AutorDTO(
        Long id,

        @NotBlank(message = "campo obrigatorio")
        String nome,

        @NotBlank(message = "campo obrigatorio")
        @Past(message = "não pode ser data futura")
        LocalDate dataNascimento,

        @NotBlank(message = "campo obrigatorio")
        String nacionalidade) {
}
