package br.com.araujo.libraryapi.livro.validator;

import br.com.araujo.libraryapi.global.exceptions.RegistroDuplicadoException;
import br.com.araujo.libraryapi.livro.model.Livro;
import br.com.araujo.libraryapi.livro.repository.LivroRepository;

import java.util.Optional;

public class LivroValidator {

    private LivroRepository livroRepository;

    public LivroValidator(LivroValidator livroValidator){
        this.livroRepository = livroRepository;
    }

    public void validar(Livro livro){
        if (existeLivroCadastrado(livro)){
            throw new RegistroDuplicadoException("Livro já cadastrado");
        }
    }

    private boolean existeLivroCadastrado(Livro livro){
        Optional<Livro> livroEncontrado = livroRepository.findByTituloAndGeneroAndDataLancamento(
                livro.getTitulo(),
                livro.getGenero(),
                livro.getDataPublicacao()
        );
        if (livro.getId() == null){
            return livroEncontrado.isPresent();
        }
        return !livro.getId().equals(livroEncontrado.get().getId()) && livroEncontrado.isPresent();
    }
}
