package br.com.araujo.libraryapi.autor.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record AutorRequestDTO(
        @NotBlank(message = "campo obrigatorio")
        String nome,

        @NotBlank(message = "campo obrigatorio")
        @Past(message = "não pode ser data futura")
        LocalDate dataNascimento,

        @NotBlank(message = "campo obrigatorio")
        String nacionalidade) {
}
