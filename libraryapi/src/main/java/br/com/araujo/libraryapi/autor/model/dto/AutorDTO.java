package br.com.araujo.libraryapi.autor.model.dto;

import java.time.LocalDate;

public record AutorDTO(
        String nome,
        LocalDate dataNacimento,
        String nacionalidade) {
}
