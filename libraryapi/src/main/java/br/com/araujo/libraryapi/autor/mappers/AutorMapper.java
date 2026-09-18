package br.com.araujo.libraryapi.autor.mappers;

import br.com.araujo.libraryapi.autor.model.Autor;
import br.com.araujo.libraryapi.autor.model.dto.AutorRequestDTO;

import br.com.araujo.libraryapi.autor.model.dto.AutorResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper{

    Autor toEntity(AutorRequestDTO autorRequestDTO);

    AutorResponseDTO toResponseDTO(Autor autor);
}
