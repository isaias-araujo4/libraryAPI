package br.com.araujo.libraryapi.usuario.mappers;

import br.com.araujo.libraryapi.usuario.model.Usuario;
import br.com.araujo.libraryapi.usuario.model.dto.UsuarioRequestDTO;

public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO);
}
