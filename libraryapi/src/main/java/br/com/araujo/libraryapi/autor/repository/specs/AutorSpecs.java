package br.com.araujo.libraryapi.autor.repository.specs;

import br.com.araujo.libraryapi.autor.model.Autor;
import org.springframework.data.jpa.domain.Specification;

public class AutorSpecs {

    public static Specification<Autor> nomeLike(String nome){
        return (root, query, cb) -> cb.like(cb.upper(root.get("nome")), "%" + nome.toUpperCase() + "%");
    }

    public static Specification<Autor> tituloLike(String titulo){
        return (root, query, cb) -> {
            return cb.like(cb.upper(root.get("livro").get("titulo")), "%" + titulo.toUpperCase() + "%");
        };
    }

    public static Specification<Autor> nacionalidadeLike(String nacionalidade){
        return (root, query, cb) -> cb.like(cb.upper(root.get("nacionalidade")), "%" + nacionalidade.toUpperCase() + "%");
    }

}
