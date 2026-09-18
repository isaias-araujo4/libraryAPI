package br.com.araujo.libraryapi.autor.service;

import br.com.araujo.libraryapi.autor.model.Autor;
import br.com.araujo.libraryapi.autor.repository.AutorRepository;
import br.com.araujo.libraryapi.autor.validator.AutorValidator;
import br.com.araujo.libraryapi.global.exceptions.OperacaoNaoPermitidaException;
import br.com.araujo.libraryapi.livro.model.Livro;
import br.com.araujo.libraryapi.livro.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.araujo.libraryapi.autor.repository.specs.AutorSpecs.*;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorValidator autorValidator;
    private final LivroRepository livroRepository;


    public  Autor salvar(Autor autor){
        autorValidator.validar(autor);
        return  autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(Long id){
        return autorRepository.findById(id);
    }

    public void deletar(Autor autor){
        if (possuiLivro(autor)){
            throw new OperacaoNaoPermitidaException("Não é permitido excluir um autor com livros cadastrados");
        }
        autorRepository.delete(autor);
    }

    public Page<Autor> pesquisa(
            String nome,
            String titulo,
            String nacionalide,
            Integer pagina,
            Integer tamanhoPagina
    ){
        Specification<Autor> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if (nome != null) {
            specs = specs.and(nomeLike(nome));
        }

        if (titulo != null){
            specs = specs.and(tituloLike(titulo));
        }

        if (nacionalide != null){
            specs = specs.and(nacionalidadeLike(nacionalide));
        }

        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);

        return autorRepository.findAll(specs, pageRequest);
    }

    public  void atualizar(Autor autor){
        if (autor.getId() == null){
            throw new IllegalArgumentException("autor não cadastrado");
        }
        autorValidator.validar(autor);
        autorRepository.save(autor);
    }

    public boolean possuiLivro(Autor autor){
        return livroRepository.existsByAutor(autor);
    }

}
