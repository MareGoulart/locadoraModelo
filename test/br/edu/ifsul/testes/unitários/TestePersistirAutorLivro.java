/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.unitários;

import br.edu.ifsul.modelo.Autor;
import br.edu.ifsul.modelo.ProdutoLivro;
import org.junit.Assert;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



/**
 *
 * @author Mary
 */
public class TestePersistirAutorLivro {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirAutorLivro() {
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
            Autor au = em.find(Autor.class, 1);
            ProdutoLivro pl = em.find(ProdutoLivro.class, 1);
            au.getAutores().add(pl);
            em.getTransaction().begin();
            em.persist(au);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);// verifico se o esperado ocorreu
    }
}
