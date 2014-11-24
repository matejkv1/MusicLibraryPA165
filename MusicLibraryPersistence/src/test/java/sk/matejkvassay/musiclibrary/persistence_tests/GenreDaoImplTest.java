/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.persistence_tests;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import javax.inject.Inject;
import static org.junit.Assert.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import sk.matejkvassay.musiclibrary.Dao.GenreDao;
import sk.matejkvassay.musiclibrary.DaoContext;
import sk.matejkvassay.musiclibrary.Entity.Genre;
import sk.matejkvassay.musiclibrary.Entity.Song;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GenreDaoImplTest {
    @PersistenceContext
    private EntityManager em;
    @Inject
    GenreDao gdi;
    @Inject
    private PlatformTransactionManager txManager;    
    
    @Before
    public void setUp() {
                
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
        song4.setGenre(genre3);

        TransactionStatus status=beginTransaction();
        
        em.persist(genre1);
        em.persist(genre2);
        em.persist(genre3);
        
        em.persist(song1);
        em.persist(song2);
        em.persist(song3);
        em.persist(song4);
        
        txManager.commit(status);
    }
    
    @After
    public void tearDown() {
        TransactionStatus status=beginTransaction();
        em.createQuery("DELETE FROM Song").executeUpdate();
        em.createQuery("DELETE FROM Genre").executeUpdate();
        txManager.commit(status);
        em.close();
    }

     @Test
     public void getAllGenresTest() {
         
        Genre genre1=new Genre();
        Genre genre2=new Genre();
        Genre genre3=new Genre();
        
        genre1.setName("Soul");
        genre2.setName("Metal");
        genre3.setName("Rock");
        
        genre1.setDescription("Soul description.");
        genre2.setDescription("Metal description.");
        genre3.setDescription("Rock description.");
        
        TransactionStatus status=beginTransaction();
        
        em.persist(genre1);
        em.persist(genre2);
        em.persist(genre3);
        
        txManager.commit(status);
        
        List<Genre> genres=gdi.getAllGenres();

        assertEquals(genres.size(),6);
        
        
     }
     
     
     @Test
     public void findGenreByNameTest(){
         
        Genre newGenre=new Genre();
        newGenre.setName("findGenreByNameTestGenre");
        newGenre.setDescription("findGenreByNameTestGenreDescription");
         
        TransactionStatus status=beginTransaction();
        em.persist(newGenre);
        txManager.commit(status);
        
        Genre foundGenre=gdi.findGenreByName("findGenreByNameTestGenre");

        assertEquals(foundGenre.getName(),newGenre.getName());  
     }
     
     @Test
     public void findGenreByIdTest(){
         
        Genre newGenre=new Genre();
        newGenre.setName("TestGenre");
        TransactionStatus status=beginTransaction();
        em.persist(newGenre);
        txManager.commit(status);
        long id=newGenre.getId();       
        Genre foundGenre=gdi.findGenreById(id);

        assertEquals(foundGenre.getId(),newGenre.getId());  
     }
     
     @Test
     public void addGenreTest(){
         TransactionStatus status=beginTransaction();
         Genre genre=new Genre();
         genre.setName("TestGenre");
         genre.setDescription("Test genre description.");
         gdi.addGenre(genre);
         txManager.commit(status);
         
         assertEquals(em.createQuery("SELECT g FROM  Genre g", Genre.class).getResultList().size(),4);
         
         
     }
     
     @Test
     public void removeGenreTest(){
         TransactionStatus status=beginTransaction();
         Genre genre=new Genre();
         genre.setName("TestGenre");
         genre.setDescription("Test genre description.");
         em.persist(genre);
         gdi.removeGenre(genre);
         txManager.commit(status);
         
         assertEquals(em.createQuery("SELECT g FROM  Genre g", Genre.class).getResultList().size(),3);         
     }
     
     @Test
     public void updateGenreTest(){
         TransactionStatus status=beginTransaction();
         Genre genre=new Genre();
         genre.setName("TestGenre");
         genre.setDescription("Not updated.");
         gdi.addGenre(genre);
         txManager.commit(status);
         
         long id=genre.getId();
         
         genre=gdi.findGenreById(id);
         genre.setDescription("updated");
         
         status=beginTransaction();
         gdi.updateGenre(genre);
         txManager.commit(status);  
         
         genre=gdi.findGenreById(id);   
         
         assertEquals(genre.getDescription(),"updated");        
         
     }     

     @Test
     public void getAllSongsTest(){
        TypedQuery query;
        query = em.createQuery("SELECT g FROM Genre g WHERE g.name=:name", Genre.class);
        query.setParameter("name", "Rap");
        Genre genre=(Genre) query.getSingleResult();
        List<Song> songs=gdi.getSongsOfGenre(genre);
        assertEquals(songs.size(),2);
     }  
     
     public TransactionStatus beginTransaction(){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        return txManager.getTransaction(def);        
     }
}
