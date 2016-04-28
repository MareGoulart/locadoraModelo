/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.unitários;

import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Produto;
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
public class TestePersistirFavorito {
      EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirFavorito() {
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
        boolean exception = false;
        try {
            Pessoa pf = em.find(Pessoa.class, 1);
            Produto p = em.find(Produto.class, 3);
            pf.getFavoritos().add(p);
            em.getTransaction().begin();
            em.persist(pf);
            em.getTransaction().commit();
        } catch(Exception e){
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);// verifico se o esperado ocorreu
    }
}
