package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "autor")
public class Autor implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_autor", sequenceName = "seq_autor_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_autor", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Length(max = 50, message = "O nome deve possuir até {max} caracteres")
    @NotBlank(message = "O nome deve ser informado")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(name = "autor_livro",
            joinColumns
            = @JoinColumn(name = "autor", referencedColumnName = "id",
                    nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "produto_livro", referencedColumnName = "id",
                    nullable = false))
    private List<ProdutoLivro> autores = new ArrayList<>();

    public Autor() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Autor other = (Autor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }

    public List<ProdutoLivro> getAutores() {
        return autores;
    }

    public void setAutores(List<ProdutoLivro> autores) {
        this.autores = autores;
    }

}
