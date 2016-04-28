/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.unitários;

import br.edu.ifsul.modelo.Editora;
import br.edu.ifsul.modelo.Gravadora;
import br.edu.ifsul.modelo.ProdutoDisco;
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
public class TestePersistirDisco {

    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirDisco() {
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
            ProdutoDisco pd = new ProdutoDisco();
            pd.setPreco(14.99);
            pd.setClassificacao(0);
            pd.setEstilo("Rock Gaucho");
            pd.setQuantidade(7);
            pd.setAlbum("MTV Bandas Gauchas");
            pd.setNumMusicas(20);
            pd.setGravadora(em.find(Gravadora.class, 1));
            
            em.getTransaction().begin();
            em.persist(pd);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);// verifico se o esperado ocorreu
    }
}
