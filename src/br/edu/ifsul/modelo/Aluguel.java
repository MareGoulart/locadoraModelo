/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Mary
 */
@Entity
@Table(name = "aluguel")
public class Aluguel implements Serializable {

    @EmbeddedId
    private AluguelId id;

    @NotNull(message = "A data do inicio do aluguel deve ser informada")
    @Temporal(TemporalType.DATE)
    @Column(name = "dataInicio", nullable = false)
    private Calendar dataInicio;

    @NotNull(message = "A data da entrega do aluguel deve ser informada")
    @Temporal(TemporalType.DATE)
    @Column(name = "dataEntrega", nullable = false)
    private Calendar dataEntrega;

    @NotNull(message = "O valor total deve ser informado")
    @Column(name = "valor_total", nullable = false, columnDefinition = "numeric(12,2)")
    private Double valorTotal;

    @NotNull(message = "O pagamento deve ser informado")
    @Column(name = "pagamento", nullable = false)
    private Boolean pagamento;

    @NotNull(message = "O valordo desconto deve ser informado")
    @Column(name = "desconto", nullable = false, columnDefinition = "numeric(12,2)")
    private Double desconto;
    
    @ManyToMany
    @JoinTable(name = "aluguel_produto",
            joinColumns
            = {@JoinColumn(name = "aluguel_pessoa", referencedColumnName = "pessoa",nullable = false),
            @JoinColumn(name = "aluguel_numero_cupom", referencedColumnName = "numero_cupom",nullable = false)},
            inverseJoinColumns
            = @JoinColumn(name = "produto", referencedColumnName = "id",
                    nullable = false))
    private List<Produto> alugueis = new ArrayList<>();
    

    public Aluguel() {
         this.valorTotal = 0.0;
    }

    
    
    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Calendar getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Calendar dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Boolean getPagamento() {
        return pagamento;
    }

    public void setPagamento(Boolean pagamento) {
        this.pagamento = pagamento;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public AluguelId getId() {
        return id;
    }

    public void setId(AluguelId id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Aluguel other = (Aluguel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<Produto> getAlugueis() {
        return alugueis;
    }

    public void setAlugueis(List<Produto> alugueis) {
        this.alugueis = alugueis;
    }

    
}
