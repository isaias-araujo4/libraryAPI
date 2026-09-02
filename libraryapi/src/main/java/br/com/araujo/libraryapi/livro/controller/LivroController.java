package br.com.araujo.libraryapi.livro.controller;

import br.com.araujo.libraryapi.autor.model.DTO.AutorDTO;
import br.com.araujo.libraryapi.global.common.GenericController;
import br.com.araujo.libraryapi.global.DTO.ErroResponse;
import br.com.araujo.libraryapi.global.exceptions.RegistroDuplicadoException;
import br.com.araujo.libraryapi.livro.mappers.LivroMapper;
import br.com.araujo.libraryapi.livro.model.GeneroLivro;
import br.com.araujo.libraryapi.livro.model.Livro;
import br.com.araujo.libraryapi.livro.model.dto.CadastroLivroDTO;
import br.com.araujo.libraryapi.livro.model.dto.ResultadoPesquisaLivroDTO;
import br.com.araujo.libraryapi.livro.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO cadastroLivroDTO){

        Livro livro = livroMapper.toEntity(cadastroLivroDTO);
        livroService.salvar(livro);
        URI location = gerarHeaderLocation(livro.getId());
        return  ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable Long id){
        Long idLivro = id;

        return livroService
                .obterPorId(idLivro)
                .map(livro ->{
                    var resultadoLivroDTO = livroMapper.toLivroDTO(livro);
                    return ResponseEntity.ok(resultadoLivroDTO);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id){
        Long idLivro = id;

        return livroService.obterPorId(idLivro)
                .map(livro -> {
                    livroService.deletar(livro);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public  ResponseEntity<List<ResultadoPesquisaLivroDTO>> pesquisa(
            @RequestParam String titulo,

            @RequestParam(value = "nome-autor")
            String nomeAutor,

            @RequestParam
            GeneroLivro genero,

            @RequestParam(value = "ano-publicacap")
            Integer anoPublicacao){
                var  resultado = livroService.pesquisa(titulo, nomeAutor, genero, anoPublicacao);
                var lista = resultado.stream().map(livroMapper::toLivroDTO).collect(Collectors.toList());

                 return ResponseEntity.ok(lista);
    }



}
