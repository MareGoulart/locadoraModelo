/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Mary
 */
@Embeddable
public class AluguelId implements Serializable {

    @NotNull(message = "O numero do cupom deve ser informada")
    @Column(name = "numero_cupom", nullable = false)
    private Integer numeroCupom;

    @NotNull(message = "A pessoa deve ser informada")
    @ManyToOne
    @JoinColumn(name = "pessoa", referencedColumnName = "id", nullable = false)
    private Pessoa pessoa;

    public AluguelId() {
    }

    public Integer getNumeroCupom() {
        return numeroCupom;
    }

    public void setNumeroCupom(Integer numeroCupom) {
        this.numeroCupom = numeroCupom;
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.numeroCupom);
        hash = 67 * hash + Objects.hashCode(this.pessoa);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AluguelId other = (AluguelId) obj;
        if (!Objects.equals(this.numeroCupom, other.numeroCupom)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        return true;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
