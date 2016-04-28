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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "produto")
public abstract class Produto implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_produto", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "O preço deve ser informado")
    @Column(name = "preco", nullable = false, columnDefinition = "decimal(12,2)")
    private Double preco;

    @NotNull(message = "A classificacao deve ser informado")
    @Column(name = "classificacao", nullable = false)
    private Integer classificacao;

    @NotBlank(message = "O estilo deve ser informado")
    @Length(max = 50, message = "O estilo não pode ter mais de {max} caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String estilo;

    @NotNull(message = "A Quantidade deve ser informado")
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    

    @ManyToMany
    @JoinTable(name = "aluguel_produto",
            joinColumns
            = @JoinColumn(name = "produto", referencedColumnName = "id",
                    nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "aluguel", referencedColumnName = "id",
                    nullable = false))
    private List<Aluguel> alugueis = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(name = "favoritos",
            joinColumns = 
            @JoinColumn(name = "produto", referencedColumnName = "id", 
                    nullable = false),
            inverseJoinColumns = 
            @JoinColumn(name = "pessoa", referencedColumnName = "id", 
                    nullable = false))    
    private List<Pessoa> favoritam = new ArrayList<>();

    public Produto() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Integer classificacao) {
        this.classificacao = classificacao;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Aluguel> getAlugueis() {
        return alugueis;
    }

    public void setAlugueis(List<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }

    public List<Pessoa> getFavoritam() {
        return favoritam;
    }

    public void setFavoritam(List<Pessoa> favoritam) {
        this.favoritam = favoritam;
    }

}
