package br.com.araujo.libraryapi.autor.model;

import br.com.araujo.libraryapi.livro.model.Livro;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "autor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"livros"})
@EntityListeners(AuditingEntityListener.class)
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String nacionalidade;

    @CreatedDate
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime dataatualizacao;

    //relação um autor para muitos livros
    @OneToMany(mappedBy = "autor")
    private List<Livro>livros;
}
