package br.com.araujo.libraryapi.livro.mappers;

import br.com.araujo.libraryapi.autor.mappers.AutorMapper;
import br.com.araujo.libraryapi.autor.model.dto.AutorResponseDTO;
import br.com.araujo.libraryapi.livro.model.GeneroLivro;
import br.com.araujo.libraryapi.livro.model.Livro;
import br.com.araujo.libraryapi.livro.model.dto.LivroRequestDTO;
import br.com.araujo.libraryapi.livro.model.dto.LivroResponseDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-18T17:24:39-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26 (Oracle Corporation)"
)
@Component
public class LivroMapperImpl implements LivroMapper {

    @Autowired
    private AutorMapper autorMapper;

    @Override
    public Livro toEntity(LivroRequestDTO livroRequestDTO) {
        if ( livroRequestDTO == null ) {
            return null;
        }

        Livro.LivroBuilder livro = Livro.builder();

        livro.titulo( livroRequestDTO.titulo() );
        livro.dataPublicacao( livroRequestDTO.dataPublicacao() );
        livro.genero( livroRequestDTO.genero() );
        livro.preco( livroRequestDTO.preco() );

        return livro.build();
    }

    @Override
    public LivroResponseDTO toLivroDTO(Livro livro) {
        if ( livro == null ) {
            return null;
        }

        Long id = null;
        String titulo = null;
        LocalDate dataPublicacao = null;
        GeneroLivro genero = null;
        BigDecimal preco = null;
        AutorResponseDTO autor = null;

        id = livro.getId();
        titulo = livro.getTitulo();
        dataPublicacao = livro.getDataPublicacao();
        genero = livro.getGenero();
        preco = livro.getPreco();
        autor = autorMapper.toResponseDTO( livro.getAutor() );

        LivroResponseDTO livroResponseDTO = new LivroResponseDTO( id, titulo, dataPublicacao, genero, preco, autor );

        return livroResponseDTO;
    }
}
