package br.com.araujo.libraryapi.autor.mappers;

import br.com.araujo.libraryapi.autor.model.Autor;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-02T20:01:49-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.2.1 (Oracle Corporation)"
)
@Component
public class AutorMapperImpl implements AutorMapper {

    @Override
    public Autor toEntity(br.com.araujo.libraryapi.autor.model.DTO.AutorRequestDTO autorRequestDTO) {
        if ( autorRequestDTO == null ) {
            return null;
        }

        Autor.AutorBuilder autor = Autor.builder();

        autor.id( autorRequestDTO.id() );
        autor.nome( autorRequestDTO.nome() );
        autor.dataNascimento( autorRequestDTO.dataNascimento() );
        autor.nacionalidade( autorRequestDTO.nacionalidade() );

        return autor.build();
    }

    @Override
    public br.com.araujo.libraryapi.autor.model.DTO.AutorRequestDTO toAutorDTO(Autor autor) {
        if ( autor == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        LocalDate dataNascimento = null;
        String nacionalidade = null;

        id = autor.getId();
        nome = autor.getNome();
        dataNascimento = autor.getDataNascimento();
        nacionalidade = autor.getNacionalidade();

        br.com.araujo.libraryapi.autor.model.DTO.AutorRequestDTO autorRequestDTO = new br.com.araujo.libraryapi.autor.model.DTO.AutorRequestDTO( id, nome, dataNascimento, nacionalidade );

        return autorRequestDTO;
    }
}
