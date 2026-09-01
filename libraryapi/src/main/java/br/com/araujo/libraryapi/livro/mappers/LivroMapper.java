package br.com.araujo.libraryapi.livro.mappers;

import br.com.araujo.libraryapi.autor.repository.AutorRepository;
import br.com.araujo.libraryapi.livro.model.Livro;
import br.com.araujo.libraryapi.livro.model.dto.CadastroLivroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java(autorRepository.findById(cadastroLivroDTO.idAutor()).orElse(null")
    public abstract Livro toEntity(CadastroLivroDTO cadastroLivroDTO);
}
