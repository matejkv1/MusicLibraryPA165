/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.persistence_tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import sk.matejkvassay.musiclibrary.DaoImpl.GenreDaoImpl;
import sk.matejkvassay.musiclibrary.Entity.Genre;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public class GenrePersistenceTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public GenrePersistenceTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        emf=Persistence.createEntityManagerFactory("TestPU");
        
        Genre genre1=new Genre();
        Genre genre2=new Genre();
        Genre genre3=new Genre();
        
        genre1.setName("Jazz");
        genre2.setName("Pop");
        genre3.setName("Rap");
        
        genre1.setDescription("Jazz description.");
        genre2.setDescription("Pop description.");
        genre3.setDescription("Rap description.");
        
        em=emf.createEntityManager();
        em.getTransaction().begin();
        
        em.persist(genre1);
        em.persist(genre2);
        em.persist(genre3);
        
        em.getTransaction().commit();
        em.close();
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }
    
    @Before
    public void setUp() {
        em=emf.createEntityManager();
    }
    
    @After
    public void tearDown() {
        em.close();
    }

    /**
     * Tests if count of rows is correct.
     */
     @Test
     public void rowCountTest() {
         GenreDaoImpl gdi=new GenreDaoImpl(em);
         List<Genre> genres=gdi.getAllGenres();
         assertEquals(genres.size(),3);
     }
}
