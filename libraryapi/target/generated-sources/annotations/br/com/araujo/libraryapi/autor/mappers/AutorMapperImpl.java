package br.com.araujo.libraryapi.autor.mappers;

import br.com.araujo.libraryapi.autor.model.Autor;
import br.com.araujo.libraryapi.autor.model.dto.AutorRequestDTO;
import br.com.araujo.libraryapi.autor.model.dto.AutorResponseDTO;
import br.com.araujo.libraryapi.livro.model.dto.LivroResponseAutorDTO;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-18T17:24:40-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26 (Oracle Corporation)"
)
@Component
public class AutorMapperImpl implements AutorMapper {

    @Override
    public Autor toEntity(AutorRequestDTO autorRequestDTO) {
        if ( autorRequestDTO == null ) {
            return null;
        }

        Autor.AutorBuilder autor = Autor.builder();

        autor.nome( autorRequestDTO.nome() );
        autor.dataNascimento( autorRequestDTO.dataNascimento() );
        autor.nacionalidade( autorRequestDTO.nacionalidade() );

        return autor.build();
    }

    @Override
    public AutorResponseDTO toResponseDTO(Autor autor) {
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

        LivroResponseAutorDTO livro = null;

        AutorResponseDTO autorResponseDTO = new AutorResponseDTO( id, nome, dataNascimento, nacionalidade, livro );

        return autorResponseDTO;
    }
}
