package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "produto_livro")
public class ProdutoLivro extends Produto implements Serializable {

    @NotBlank(message = "O titulo deve ser informado")
    @Length(max = 50, message = "O titulo não deve ter mais que {max} caracteres")
    @Column(name = "titulo", length = 50, nullable = false)
    private String titulo;
    
    @NotNull(message = "O numero de paginas deve ser informado")
    @Column(name = "numPaginas", nullable = false)    
    private Integer numPaginas;
    
    @NotNull(message = "A editora deve ser informado")
    @ManyToOne
    @JoinColumn(name = "editora", referencedColumnName = "id", nullable = false)
    private Editora editora;
    
    @ManyToMany
    @JoinTable(name = "autor_livro",
            joinColumns = 
            @JoinColumn(name = "produto_livro", referencedColumnName = "id", 
                    nullable = false),
            inverseJoinColumns = 
            @JoinColumn(name = "autor", referencedColumnName = "id", 
                    nullable = false))    
    private List<Autor> autores = new ArrayList<>();

    public ProdutoLivro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(Integer numPaginas) {
        this.numPaginas = numPaginas;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }


}
