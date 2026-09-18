package br.com.araujo.libraryapi.autor.controller;

import br.com.araujo.libraryapi.autor.mappers.AutorMapper;
import br.com.araujo.libraryapi.autor.model.Autor;
import br.com.araujo.libraryapi.autor.model.dto.AutorRequestDTO;
import br.com.araujo.libraryapi.autor.model.dto.AutorResponseDTO;
import br.com.araujo.libraryapi.autor.service.AutorService;
import br.com.araujo.libraryapi.global.common.GenericController;
import br.com.araujo.libraryapi.livro.model.dto.LivroResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("autores") // http://localhost:8080/autores
@RequiredArgsConstructor
public class AutorController implements GenericController {

    private final AutorService autorService;
    private final AutorMapper autorMapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid AutorRequestDTO autorRequestDTO) {

        Autor autor = autorMapper.toEntity(autorRequestDTO);
        autorService.salvar(autor);
        URI location = gerarHeaderLocation(autor.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorResponseDTO> obterDetalhes(@PathVariable Long id) {
        Long idAutor = id;

        return autorService
                .obterPorId(idAutor)
                .map(autor -> {
                    var autorResponseDTO = autorMapper.toResponseDTO(autor);
                    return ResponseEntity.ok(autorResponseDTO);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        Long idAutor = id;

        return autorService.obterPorId(idAutor)
                .map(autor -> {
                    autorService.deletar(autor);
                    return ResponseEntity.ok(AutorResponseDTO);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<AutorResponseDTO>> pesquisa(
            @RequestParam String nome,

            @RequestParam(value = "titulo")
            String titulo,

            @RequestParam
            String nacionalidade,

            @RequestParam(value = "pagina", defaultValue = "0")
            Integer pagina,

            @RequestParam(value = "tamanho-pagina", defaultValue = "10")
            Integer tamanhoPagina
    ) {
        Page<Autor> paginaResultado = autorService.pesquisa(nome, titulo, nacionalidade, pagina, tamanhoPagina);

        Page<AutorResponseDTO> resultado = paginaResultado.map(autorMapper::toResponseDTO);

        return ResponseEntity.ok(resultado);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody @Valid AutorRequestDTO autorRequestDto) {

        Long idAutor = id;

        return autorService.obterPorId(idAutor)
                .map(autor ->{
            Autor entidadeAuxiliar = autorMapper.toEntity(autorRequestDto);
            autor.setNome(entidadeAuxiliar.getNome());
            autor.setDataNascimento(entidadeAuxiliar.getDataNascimento());
            autor.setNacionalidade(entidadeAuxiliar.getNacionalidade());

            autorService.atualizar(autor);

            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}