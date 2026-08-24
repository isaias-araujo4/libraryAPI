package br.com.araujo.libraryapi.repository;

import br.com.araujo.libraryapi.model.Autor;
import br.com.araujo.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void  salvarAutorTest(){
        Autor autor = new Autor();
        autor.setNome("J.K");
        autor.setNacionalidade("britanica");
        autor.setDataNascimento(LocalDate.of(2000, 12, 8));

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarAutorTest(){
        Long id = 1L;

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if (possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor");
            System.out.println(autorEncontrado);

            autorEncontrado.setNome("isaias");

            autorRepository.save(autorEncontrado);
        }
    }

    @Test
    public void listarAutorTest(){
        List<Autor> lista = autorRepository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void  contagemTest(){
        System.out.println("Contagem de autores: " + autorRepository.count());
    }

    @Test
    public  void deletarAutorPorId(){
        Long id = 1L;
        autorRepository.deleteById(id);
    }

    @Test
    public void listarLivrosPorAutor(){
        Long id = 3L;
        var  autor = autorRepository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }

}
