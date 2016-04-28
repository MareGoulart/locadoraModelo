/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.unitários;

import br.edu.ifsul.modelo.Editora;
import br.edu.ifsul.modelo.ProdutoLivro;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mary
 */
public class TestePersistirLivro {

    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirLivro() {
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
    public void teste() {
        boolean exception = false;
        try {
            ProdutoLivro pl = new ProdutoLivro();
            pl.setPreco(39.90);
            pl.setClassificacao(16);
            pl.setEstilo("Acao");
            pl.setQuantidade(12);
            pl.setTitulo("Game of Thrones");
            pl.setNumPaginas(359);
            pl.setEditora(em.find(Editora.class, 1));
            
            em.getTransaction().begin();
            em.persist(pl);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);// verifico se o esperado ocorreu
    }
}
