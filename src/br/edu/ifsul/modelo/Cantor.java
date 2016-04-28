
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
@Table(name = "cantor")
public class Cantor implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_cantor",sequenceName = "seq_cantor_id",allocationSize = 1)
    @GeneratedValue(generator = "seq_cantor",strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Length(max = 50, message = "O nome deve possuir até {max} caracteres")
    @NotBlank(message = "O nome deve ser informado")    
    @Column(name = "nome",length = 50, nullable = false)
    private String nome;
    
    @ManyToMany
    @JoinTable(name = "cantor_disco",
            joinColumns
            = @JoinColumn(name = "cantor", referencedColumnName = "id",
                    nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "produto_disco", referencedColumnName = "id",
                    nullable = false))
    private List<ProdutoDisco> cantores = new ArrayList<>();
   
    
    public Cantor(){
        
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
        final Cantor other = (Cantor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }

    public List<ProdutoDisco> getCantores() {
        return cantores;
    }

    public void setCantores(List<ProdutoDisco> cantores) {
        this.cantores = cantores;
    }
    
    
}
