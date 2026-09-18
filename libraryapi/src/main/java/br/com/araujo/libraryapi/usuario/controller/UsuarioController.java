package br.com.araujo.libraryapi.usuario.controller;

import br.com.araujo.libraryapi.usuario.mappers.UsuarioMapper;
import br.com.araujo.libraryapi.usuario.model.dto.UsuarioRequestDTO;
import br.com.araujo.libraryapi.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody UsuarioRequestDTO usuarioRequestDTO){
        var usuario = usuarioMapper.toEntity(usuarioRequestDTO);
        usuarioService.salvar(usuario);
    }
}
