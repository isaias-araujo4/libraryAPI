package br.com.araujo.libraryapi.autor.controller;

import br.com.araujo.libraryapi.autor.mappers.AutorMapper;
import br.com.araujo.libraryapi.autor.model.Autor;
import br.com.araujo.libraryapi.autor.model.dto.AutorDTO;
import br.com.araujo.libraryapi.autor.service.AutorService;
import br.com.araujo.libraryapi.global.common.GenericController;
import br.com.araujo.libraryapi.global.DTO.ErroResponse;
import br.com.araujo.libraryapi.global.exceptions.OperacaoNaoPermitidaException;
import br.com.araujo.libraryapi.global.exceptions.RegistroDuplicadoException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public  ResponseEntity<Void> salvar(@RequestBody @Valid AutorDTO autorDTO){

        Autor autor = autorMapper.toEntity(autorDTO);
        autorService.salvar(autor);
        URI location = gerarHeaderLocation(autor.getId());
        return  ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable Long id){
        Long idAutor = id;

        return  autorService
                .obterPorId(idAutor)
                .map(autor -> {
                    AutorDTO autorDTO = autorMapper.toAutorDTO(autor);
                    return ResponseEntity.ok(autorDTO);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){

        Long idAutor = id;
        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);

        if (autorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        autorService.deletar(autorOptional.get());

        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public  ResponseEntity<List<AutorDTO>> pesquisar(@RequestParam(value = "nome", required = false) String nome, @RequestParam(value = "nacionalidade", required = false)String nacionalidade){
        List<Autor> resultado = autorService.pesquisa(nome, nacionalidade);
        List<AutorDTO> lista = resultado
                .stream()
                .map(autorMapper::toAutorDTO).collect(Collectors.toList());
        return  ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody @Valid AutorDTO autorDto) {

        Long idAutor = id;
        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);

        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var autor = autorOptional.get();
        autor.setNome(autorDto.nome());
        autor.setNacionalidade(autorDto.nacionalidade());
        autor.setDataNascimento(autorDto.dataNascimento());

        autorService.atualizar(autor);

        return ResponseEntity.noContent().build();

    }
}
