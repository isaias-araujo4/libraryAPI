package br.com.araujo.libraryapi.autor.model.dto;

import br.com.araujo.libraryapi.autor.model.Autor;

import java.time.LocalDate;

public record AutorDto(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String nacionalidade) {
    public Autor mapearParaAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
