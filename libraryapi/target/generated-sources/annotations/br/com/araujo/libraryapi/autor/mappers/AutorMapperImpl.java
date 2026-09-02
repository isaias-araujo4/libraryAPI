package br.com.araujo.libraryapi.autor.mappers;

import br.com.araujo.libraryapi.autor.model.Autor;
import br.com.araujo.libraryapi.autor.model.dto.AutorDTO;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-02T19:33:08-0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.100.v20260624-0231, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class AutorMapperImpl implements AutorMapper {

    @Override
    public Autor toEntity(AutorDTO autorDto) {
        if ( autorDto == null ) {
            return null;
        }

        Autor.AutorBuilder autor = Autor.builder();

        autor.dataNascimento( autorDto.dataNascimento() );
        autor.id( autorDto.id() );
        autor.nacionalidade( autorDto.nacionalidade() );
        autor.nome( autorDto.nome() );

        return autor.build();
    }

    @Override
    public AutorDTO toAutorDTO(Autor autor) {
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

        AutorDTO autorDTO = new AutorDTO( id, nome, dataNascimento, nacionalidade );

        return autorDTO;
    }
}
