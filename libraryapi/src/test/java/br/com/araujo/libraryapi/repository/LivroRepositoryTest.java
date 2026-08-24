package br.com.araujo.libraryapi.repository;

import br.com.araujo.libraryapi.model.Autor;
import br.com.araujo.libraryapi.model.GeneroLivro;
import br.com.araujo.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class LivroRepositoryTest {


    @Autowired
    LivroRepository livroRepository;


    @Autowired
    AutorRepository autoroRepository;

    @Test
    public void salvarLivroTest(){
        Livro livro = new Livro();
        livro.setIsbn("1234-5678");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("HP 2");
        livro.setDataPublicacao(LocalDate.of(2000, 8, 10));

        Long id = 3L ;
        Autor autor = autoroRepository.findById(id).orElse(null);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    public void atualizarAutorDoLivroTest(){
        Long id = 1L;
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        Long idAutor = 3L;
        Autor jose = autoroRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(jose);

        livroRepository.save(livroParaAtualizar);
    }

    @Test
    public void deletarLivroPorId(){

        Long id = 1L;

        livroRepository.deleteById(id);
    }

    @Test
    @Transactional
    public void buscarLivroTest(){
        Long id = 1L;
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println("Livro:");
        System.out.println(livro.getTitulo());

        System.out.println("Autor:");
        System.out.println(livro.getAutor().getNome());
    }

}
