/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.persistence_tests;

import java.util.ArrayList;
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
import sk.matejkvassay.musiclibrary.Entity.Song;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public class GenrePersistenceTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static GenreDaoImpl gdi;
    
    @BeforeClass
    public static void setUpClass() {
        emf=Persistence.createEntityManagerFactory("TestPU");
    }
    
    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }
    
    @Before
    public void setUp() {
        
        em=emf.createEntityManager();
        
        gdi=new GenreDaoImpl(em);
                
        Genre genre1=new Genre();
        Genre genre2=new Genre();
        Genre genre3=new Genre();
        
        Song song1=new Song();
        Song song2=new Song();
        Song song3=new Song();
        Song song4=new Song();
        
        genre1.setName("Jazz");
        genre2.setName("Pop");
        genre3.setName("Rap");
        
        genre1.setDescription("Jazz description.");
        genre2.setDescription("Pop description.");
        genre3.setDescription("Rap description.");

        song1.setBitrate(320);
        song1.setCommentary("Jazz song commentary.");
        song1.setPositionInAlbum(1);
        song1.setTitle("Jazz song title..");
        
        song2.setBitrate(320);
        song2.setCommentary("Pop song commentary.");
        song2.setPositionInAlbum(2);
        song2.setTitle("Pop song title.");
        
        song3.setBitrate(320);
        song3.setCommentary("Rap song commentary.");
        song3.setPositionInAlbum(3);
        song3.setTitle("Rap song title.");
        
        song4.setBitrate(320);
        song4.setCommentary("Second rap song commentary.");
        song4.setPositionInAlbum(3);
        song4.setTitle("Second rap song title.");
        
        List<Song> songs1 = new ArrayList<Song>();
        List<Song> songs2 = new ArrayList<Song>();  
        List<Song> songs3 = new ArrayList<Song>();
        
        songs1.add(song1);
        songs2.add(song2);
        songs3.add(song3);
        songs3.add(song4);
        
        genre1.setSongs(songs1);
        genre2.setSongs(songs2);
        genre3.setSongs(songs3);
        
        song1.setGenre(genre1);
        song2.setGenre(genre2);
        song3.setGenre(genre3);

        em.getTransaction().begin();
        
        em.persist(genre1);
        em.persist(genre2);
        em.persist(genre3);
        
        em.persist(song1);
        em.persist(song2);
        em.persist(song3);
        em.persist(song4);
        
        em.getTransaction().commit();
    }
    
    @After
    public void tearDown() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Song").executeUpdate();
        em.createQuery("DELETE FROM Genre").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Checks if count of rows is correct.
     */
     @Test
     public void rowCountTest() {
         
        Genre genre1=new Genre();
        Genre genre2=new Genre();
        Genre genre3=new Genre();
        
        genre1.setName("Soul");
        genre2.setName("Metal");
        genre3.setName("Rock");
        
        genre1.setDescription("Soul description.");
        genre2.setDescription("Metal description.");
        genre3.setDescription("Rock description.");
        
        em=emf.createEntityManager();
        em.getTransaction().begin();
        
        em.persist(genre1);
        em.persist(genre2);
        em.persist(genre3);
        
        em.getTransaction().commit();
        
        List<Genre> genres=gdi.getAllGenres();

        assertEquals(genres.size(),6);
        
        
     }
     
     
     @Test
     public void findGenreByNameTest(){
         
        Genre newGenre=new Genre();
        newGenre.setName("findGenreByNameTestGenre");
        newGenre.setDescription("findGenreByNameTestGenreDescription");
         
        em.getTransaction().begin();
        em.persist(newGenre);
        em.getTransaction().commit();
        
        Genre foundGenre=gdi.findGenreByName("findGenreByNameTestGenre");

        assertEquals(foundGenre.getName(),newGenre.getName());
         

         
     }
//      
//     
//     @Test
//     public void addAndRemoveTest(){
//         em.getTransaction().begin();
//         
//         
//     }
}
