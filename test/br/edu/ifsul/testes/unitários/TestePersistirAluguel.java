/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.unitários;

import br.edu.ifsul.modelo.Aluguel;
import br.edu.ifsul.modelo.AluguelId;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Produto;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Mary
 */

//// PRECISA SER TESTADO!!!! CORRIGIR


public class TestePersistirAluguel {
     EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirAluguel() {
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("LocadoraModeloPU");
        em = emf.createEntityManager();
    }
    
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    
    @Test
    public void teste(){
        // o teste não deve gerar exceção se tudo estiver correto
        boolean exception = false;
        try {
            Aluguel a = new Aluguel();
            AluguelId id = new AluguelId();
            Produto p = em.find(Produto.class, 1);
      //      a.getAlugueis().add(p);
            id.setNumeroCupom(98765432);
            id.setPessoa(em.find(Pessoa.class, 1));
            a.setId(id);
            a.setDataInicio(Calendar.getInstance());
            a.setDataEntrega(Calendar.getInstance());
            a.setPagamento(false);
            a.setValorTotal(10.90);
            a.setDesconto(0.10);
            
//            Compra c = new Compra();
//            CompraID id = new CompraID();
//            id.setNumeroNota(1654459989);
//            id.setPessoa(em.find(PessoaFisica.class,1));
//            c.setId(id);
//            c.setData(Calendar.getInstance());
//            //p.setValorTotal(500.00);
//            CompraItem item = new CompraItem();
//            item.setProduto(em.find(Produto.class, 1));
//            item.setQuantidade(3.0);
//            item.setValorUnitario(item.getProduto().getPreco());
//            item.setValorTotal(item.getQuantidade()*item.getValorUnitario());
//            c.adicionarItem(item);
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        }catch (Exception e){
            // se gerar exceção 
            exception = true;
            e.printStackTrace();
        }
        // compara se não ocorreu erro
        Assert.assertEquals(false, exception);
    }
    
}
