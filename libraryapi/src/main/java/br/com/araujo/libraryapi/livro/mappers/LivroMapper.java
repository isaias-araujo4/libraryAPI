package br.com.araujo.libraryapi.livro.mappers;

import br.com.araujo.libraryapi.autor.mappers.AutorMapper;
import br.com.araujo.libraryapi.livro.model.Livro;
import br.com.araujo.libraryapi.livro.model.dto.LivroRequestDTO;
import br.com.araujo.libraryapi.livro.model.dto.LivroResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AutorMapper.class)
public interface LivroMapper {

    @Mapping(target = "autor", ignore = true)
    Livro toEntity(LivroRequestDTO livroRequestDTO);

    LivroResponseDTO toLivroDTO(Livro livro);
}