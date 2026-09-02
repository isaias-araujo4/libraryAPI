package br.com.araujo.libraryapi.livro.repository.specs;

import br.com.araujo.libraryapi.livro.model.GeneroLivro;
import br.com.araujo.libraryapi.livro.model.Livro;
import jakarta.persistence.criteria.JoinType;
import org.hibernate.mapping.Join;
import org.springframework.data.jpa.domain.Specification;

public class LivroSpecs {

    public  static Specification<Livro> tituloLike(String titulo){
        return  (root, query, cb)
        -> cb.like(cb.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%");
    }

    public static  Specification<Livro> generoEqual(GeneroLivro generoLivro){
        return (root, query, cb) -> cb.equal(root.get("genero"), generoLivro);
    }

    public static  Specification<Livro> anoPublicacapEqual(Integer anoPublicacao){
        return (root, query, cb)
                -> cb.equal(cb.function("to_char", String.class,
                root.get("dataPublicacao"), cb.literal("yyyy")),
                anoPublicacao.toString());
    }

    public  static Specification<Livro> nomeAutorLike(String nome) {
        return (root, query, cb) -> {
            return cb.like(cb.upper(root.get("autor").get("nome")),
                    "%" + nome.toUpperCase() + "%");
        };
    }
}
