package br.com.araujo.libraryapi.model;


import jakarta.persistence.*;
import lombok.*;

import javax.lang.model.element.Name;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "livro")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "autor")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GeneroLivro genero;

    @Column(precision = 18, scale = 2)
    private BigDecimal preco;

    //relação muitos livros para um autor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor")
    private  Autor autor;
}
