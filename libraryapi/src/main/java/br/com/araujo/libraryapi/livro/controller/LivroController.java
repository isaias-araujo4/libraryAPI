package br.com.araujo.libraryapi.livro.controller;

import br.com.araujo.libraryapi.global.common.GenericController;
import br.com.araujo.libraryapi.livro.mappers.LivroMapper;
import br.com.araujo.libraryapi.livro.model.GeneroLivro;
import br.com.araujo.libraryapi.livro.model.Livro;
import br.com.araujo.libraryapi.livro.model.dto.LivroRequestDTO;
import br.com.araujo.libraryapi.livro.model.dto.LivroResponseDTO;
import br.com.araujo.libraryapi.livro.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    @PostMapping
    @PreAuthorize("hasAnyrole('ADMIN')")
    public ResponseEntity<Void> salvar(@RequestBody @Valid LivroRequestDTO livroRequestDTO){

        Livro livro = livroMapper.toEntity(livroRequestDTO);
        livroService.salvar(livro);
        URI location = gerarHeaderLocation(livro.getId());
        return  ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<LivroResponseDTO> obterDetalhes(@PathVariable Long id){
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
    public  ResponseEntity<Page<LivroResponseDTO>> pesquisa(
            @RequestParam String titulo,

            @RequestParam(value = "nome-autor")
            String nomeAutor,

            @RequestParam
            GeneroLivro genero,

            @RequestParam(value = "ano-publicacap")
            Integer anoPublicacao,

            @RequestParam(value = "pagina", defaultValue = "0")
            Integer pagina,

            @RequestParam(value = "tamanho-pagina", defaultValue = "10")
            Integer tamanhoPagina) {
                Page<Livro> paginaResultado = livroService.pesquisa(titulo, nomeAutor, genero, anoPublicacao, pagina, tamanhoPagina);

                Page<LivroResponseDTO> resultado = paginaResultado.map(livroMapper::toLivroDTO);


                 return ResponseEntity.ok(resultado);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody LivroRequestDTO livroRequestDTO){
        Long idLivro = id;

        return livroService.obterPorId(idLivro)
                .map(livro -> {
                    Livro entidadeAuxiliar = livroMapper.toEntity(livroRequestDTO);
                    livro.setDataPublicacao(entidadeAuxiliar.getDataPublicacao());
                    livro.setPreco(entidadeAuxiliar.getPreco());
                    livro.setGenero(entidadeAuxiliar.getGenero());
                    livro.setTitulo(entidadeAuxiliar.getTitulo());
                    livro.setAutor(entidadeAuxiliar.getAutor());

                    livroService.atualizar(livro);

                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
