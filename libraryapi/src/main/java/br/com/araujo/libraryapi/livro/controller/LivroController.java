package br.com.araujo.libraryapi.livro.controller;

import br.com.araujo.libraryapi.global.dto.ErroResponse;
import br.com.araujo.libraryapi.global.exeptions.RegistroDuplicadoException;
import br.com.araujo.libraryapi.livro.mappers.LivroMapper;
import br.com.araujo.libraryapi.livro.model.Livro;
import br.com.araujo.libraryapi.livro.model.dto.CadastroLivroDTO;
import br.com.araujo.libraryapi.livro.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO cadastroLivroDTO){
        try {
            Livro livro = livroMapper.toEntity(cadastroLivroDTO);
            livroService.salvar(livro);
            return  ResponseEntity.ok(cadastroLivroDTO);
        }catch (RegistroDuplicadoException e){
            var erroDTO = ErroResponse.conflito(e.getMessage());
            return  ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}
