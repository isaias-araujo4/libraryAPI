package br.com.araujo.libraryapi.autor.controller;

import br.com.araujo.libraryapi.autor.model.Autor;
import br.com.araujo.libraryapi.autor.model.dto.AutorDto;
import br.com.araujo.libraryapi.autor.service.AutorService;
import br.com.araujo.libraryapi.global.dto.ErroResponse;
import br.com.araujo.libraryapi.global.exeptions.OperacaoNaoPermitidaException;
import br.com.araujo.libraryapi.global.exeptions.RegistroDuplicadoException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("autores") // http://localhost:8080/autores
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @PostMapping
    public  ResponseEntity<Object> salvar(@RequestBody @Valid AutorDto autor){
        try {
        Autor autorEntidade = autor.mapearParaAutor();
        autorService.salvar(autorEntidade);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorEntidade.getId())
                .toUri();

        return  ResponseEntity.created(location).build();
        } catch(RegistroDuplicadoException e){
            var erroDto = ErroResponse.conflito(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDto> obterDetalhes(@PathVariable Long id){
        Long idAutor = id;
        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);

        if (autorOptional.isPresent()){
            Autor autor = autorOptional.get();
            AutorDto dto = new AutorDto(autor.getId(),
                    autor.getNome(),
                    autor.getDataNascimento(),
                    autor.getNacionalidade());
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id){
        try {

        Long idAutor = id;
        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);

        if (autorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        autorService.deletar(autorOptional.get());

        return ResponseEntity.noContent().build();
        }catch (OperacaoNaoPermitidaException e){
            var  erroResponse = ErroResponse.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroResponse.status()).body(erroResponse);
        }
    }

    @GetMapping
    public  ResponseEntity<List<AutorDto>> pesquisar(@RequestParam(value = "nome", required = false) String nome, @RequestParam(value = "nacionalidade", required = false)String nacionalidade){
        List<Autor> resultado = autorService.pesquisa(nome, nacionalidade);
        List<AutorDto> lista = resultado
                .stream()
                .map(autor -> new AutorDto(
                        autor.getId(),
                        autor.getNome(),
                        autor.getDataNascimento(),
                        autor.getNacionalidade()
                )).collect(Collectors.toList());
        return  ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody @Valid AutorDto autorDto) {
        try {
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
        } catch (RegistroDuplicadoException e) {
            var erroDto = ErroResponse.conflito(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }
}
