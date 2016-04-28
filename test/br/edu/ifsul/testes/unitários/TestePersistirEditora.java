/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.unitários;

import br.edu.ifsul.modelo.Editora;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Mary
 */
public class TestePersistirEditora {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirEditora() {
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
            Editora e = new Editora();

            e.setNome("Globo");
           
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);// verifico se o esperado ocorreu
    }
}
