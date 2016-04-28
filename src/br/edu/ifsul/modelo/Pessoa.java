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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Mary
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_pessoa", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank(message = "O nome deve ser informado")
    @Length(max = 50, message = "O nome não deve ter mais que {max} caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Email(message = "Informe um email válido")
    @NotBlank(message = "O email deve ser informado")
    @Length(max = 50, message = "O email não deve ter mais que {max} caracteres")
    private String email;

    @NotNull(message = "O Nascimento deve ser informado")
    @Temporal(TemporalType.DATE)
    @Column(name = "nascimento", nullable = false)
    private Calendar nascimento;

    @NotBlank(message = "A senha deve ser informada")
    @Length(max = 10, message = "A senha não deve ter mais que {max} caracteres")
    @Column(name = "senha", length = 10, nullable = false)
    private String senha;

    @CPF(message = "Informe um CPF válido")
    @NotBlank(message = "O CPF deve ser informado")
    @Length(max = 14, message = "O CPF não deve ter mais que {max} caracteres")
    @Column(name = "cpf", length = 14, nullable = false)
    private String cpf;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Telefone> telefones = new ArrayList<>();

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Dependente> dependentes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "favoritos",
            joinColumns
            = @JoinColumn(name = "pessoa", referencedColumnName = "id",
                    nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "produto", referencedColumnName = "id",
                    nullable = false))
    private List<Produto> favoritos = new ArrayList<>();

    public Pessoa() {
    }

    public void adicionarTelefone(Telefone obj) {
        obj.setPessoa(this);
        this.telefones.add(obj);
    }

    public void removerTelefone(int index) {
        this.telefones.remove(index);
    }

    public void adicionarDependente(Dependente obj) {
        obj.setPessoa(this);
        this.dependentes.add(obj);
    }

    public void removerDependente(int index) {
        this.dependentes.remove(index);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getNascimento() {
        return nascimento;
    }

    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }

    public void setDependentes(List<Dependente> dependentes) {
        this.dependentes = dependentes;
    }

    public List<Produto> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Produto> favoritos) {
        this.favoritos = favoritos;
    }

}
