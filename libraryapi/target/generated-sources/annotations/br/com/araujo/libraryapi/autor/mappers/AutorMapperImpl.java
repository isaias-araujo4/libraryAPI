package br.com.araujo.libraryapi.autor.mappers;

import br.com.araujo.libraryapi.autor.model.Autor;
import br.com.araujo.libraryapi.autor.model.DTO.AutorDTO;
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
    public Autor toEntity(AutorDTO autorDTO) {
        if ( autorDTO == null ) {
            return null;
        }

        Autor.AutorBuilder autor = Autor.builder();

        autor.id( autorDTO.id() );
        autor.nome( autorDTO.nome() );
        autor.dataNascimento( autorDTO.dataNascimento() );
        autor.nacionalidade( autorDTO.nacionalidade() );

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
