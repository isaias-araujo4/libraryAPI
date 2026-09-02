package br.com.araujo.libraryapi.livro.repository.specs;

import br.com.araujo.libraryapi.livro.model.GeneroLivro;
import br.com.araujo.libraryapi.livro.model.Livro;
import org.hibernate.mapping.Join;
import org.springframework.data.jpa.domain.Specification;

public class LivroSpecs {

    public  static Specification<Livro> tituloLike(String titulo){
        return  (root, query, cb)
        -> cb.like(cb.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%");
    }

 
}
