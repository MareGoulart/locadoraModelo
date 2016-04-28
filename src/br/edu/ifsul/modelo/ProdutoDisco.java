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
@Table(name = "produto_disco")
public class ProdutoDisco extends Produto implements Serializable {

    @NotBlank(message = "O Album deve ser informado")
    @Length(max = 50, message = "O Album não deve ter mais que {max} caracteres")
    @Column(name = "album", length = 50, nullable = false)
    private String album;

    @NotNull(message = "O numero de musicas deve ser informado")
    @Column(name = "numMusicas", nullable = false)
    private Integer numMusicas;

    @NotNull(message = "A gravadora deve ser informado")
    @ManyToOne
    @JoinColumn(name = "gravadora", referencedColumnName = "id", nullable = false)
    private Gravadora gravadora;

    @ManyToMany
    @JoinTable(name = "gravador",
            joinColumns
            = @JoinColumn(name = "produto_disco", referencedColumnName = "id",
                    nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "gravadora", referencedColumnName = "id",
                    nullable = false))
    private List<Gravadora> gravadoras = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "cantor_disco",
            joinColumns
            = @JoinColumn(name = "produto_disco", referencedColumnName = "id",
                    nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "cantor", referencedColumnName = "id",
                    nullable = false))
    private List<Cantor> cantores = new ArrayList<>();

    public ProdutoDisco() {
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getNumMusicas() {
        return numMusicas;
    }

    public void setNumMusicas(Integer numMusicas) {
        this.numMusicas = numMusicas;
    }

    public Gravadora getGravadora() {
        return gravadora;
    }

    public void setGravadora(Gravadora gravadora) {
        this.gravadora = gravadora;
    }

    public List<Gravadora> getGravadoras() {
        return gravadoras;
    }

    public void setGravadoras(List<Gravadora> gravadoras) {
        this.gravadoras = gravadoras;
    }

}
