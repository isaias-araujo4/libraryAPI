package br.com.araujo.libraryapi.livro.mappers;

import br.com.araujo.libraryapi.autor.mappers.AutorMapper;
import br.com.araujo.libraryapi.autor.model.DTO.AutorDTO;
import br.com.araujo.libraryapi.livro.model.GeneroLivro;
import br.com.araujo.libraryapi.livro.model.Livro;
import br.com.araujo.libraryapi.livro.model.dto.CadastroLivroDTO;
import br.com.araujo.libraryapi.livro.model.dto.ResultadoPesquisaLivroDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-02T20:01:48-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.2.1 (Oracle Corporation)"
)
@Component
public class LivroMapperImpl extends LivroMapper {

    @Autowired
    private AutorMapper autorMapper;

    @Override
    public Livro toEntity(CadastroLivroDTO cadastroLivroDTO) {
        if ( cadastroLivroDTO == null ) {
            return null;
        }

        Livro.LivroBuilder livro = Livro.builder();

        livro.titulo( cadastroLivroDTO.titulo() );
        livro.dataPublicacao( cadastroLivroDTO.dataPublicacao() );
        livro.genero( cadastroLivroDTO.genero() );
        livro.preco( cadastroLivroDTO.preco() );

        livro.autor( autorRepository.findById(cadastroLivroDTO.idAutor()).orElse(null) );

        return livro.build();
    }

    @Override
    public ResultadoPesquisaLivroDTO toLivroDTO(Livro livro) {
        if ( livro == null ) {
            return null;
        }

        Long id = null;
        String titulo = null;
        LocalDate dataPublicacao = null;
        GeneroLivro genero = null;
        BigDecimal preco = null;
        AutorDTO autor = null;

        id = livro.getId();
        titulo = livro.getTitulo();
        dataPublicacao = livro.getDataPublicacao();
        genero = livro.getGenero();
        preco = livro.getPreco();
        autor = autorMapper.toAutorDTO( livro.getAutor() );

        ResultadoPesquisaLivroDTO resultadoPesquisaLivroDTO = new ResultadoPesquisaLivroDTO( id, titulo, dataPublicacao, genero, preco, autor );

        return resultadoPesquisaLivroDTO;
    }
}
