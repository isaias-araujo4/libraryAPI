package br.com.araujo.libraryapi.autor.mappers;

import br.com.araujo.libraryapi.autor.model.Autor;
import br.com.araujo.libraryapi.autor.model.DTO.AutorDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper{

    Autor toEntity(AutorDTO autorDTO);

    AutorDTO toAutorDTO(Autor autor);
}
