/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.LoadState;
import org.hibernate.ejb.util.PersistenceUtilHelper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sk.matejkvassay.musiclibrary.DaoImpl.AlbumDaoImpl;
import sk.matejkvassay.musiclibrary.Entity.Album;
import sk.matejkvassay.musiclibrary.Entity.Musician;
import sk.matejkvassay.musiclibrary.Entity.Song;


/**
 *
 * @author Red
 */
public class AlbumDaoImplTest {
    
    private static EntityManagerFactory emf;
    private EntityManager em;
    AlbumDaoImpl albumDao;
    
    public AlbumDaoImplTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("TestPU");
    }
    
    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }
    
    @Before
    public void setUp() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        albumDao = new AlbumDaoImpl(em);
        
    }
    
    @After
    public void tearDown() {
        if (em.isOpen()) em.close();
    }

    
    @Test
    public void addAlbumTest() {
        Album alb = new Album();
        alb.setTitle("alb");
        albumDao.addAlbum(alb);
        em.getTransaction().commit();
        em.close();
        
        System.out.println("persistence util helper loaded state: " + PersistenceUtilHelper.isLoaded(alb));
        assertEquals("Album is not loaded.", PersistenceUtilHelper.isLoaded(alb), LoadState.LOADED);
        
    }
    
    @Test
    public void getAllAlbumsTest() {
        Album a1 = new Album();
        a1.setTitle("a1");
        Album a2 = new Album();
        a2.setTitle("a2");
        Album a3 = new Album();
        a3.setTitle("a3");
        Album a4 = new Album();
        a4.setTitle("a4");
        albumDao.addAlbum(a1);
        albumDao.addAlbum(a2);
        albumDao.addAlbum(a3);
        albumDao.addAlbum(a4);
        em.getTransaction().commit();
        
        Set <Album> result = albumDao.getAllAlbums();
        
        assertNotNull("getAllAlbums() returned null, should be 4.", result);
        assertEquals("getAllAlbums() didn't return 4 albums as expected.", result.size(), 4);
        assertTrue("getAllAlbums() didn't include a1.", result.contains(a1));
        assertTrue("getAllAlbums() didn't include a2.", result.contains(a2));
        assertTrue("getAllAlbums() didn't include a3.", result.contains(a3));
        assertTrue("getAllAlbums() didn't include a4.", result.contains(a4));
    }
    
    @Test
    public void getAlbumByIdTest() {
        Album a1 = new Album();
        a1.setTitle("a1");
        Album a2 = new Album();
        a2.setTitle("a2");
        Album a3 = new Album();
        a3.setTitle("a3");
        albumDao.addAlbum(a1);
        albumDao.addAlbum(a2);
        albumDao.addAlbum(a3);
        em.getTransaction().commit();
        
        assertEquals("Didn't return same ID album 2.", albumDao.getAlbumById(a2.getId()), a2.getId());
        assertEquals("Didn't return same ID album 3.", albumDao.getAlbumById(a3.getId()), a3.getId());
        assertEquals("Didn't return same ID album 1.", albumDao.getAlbumById(a1.getId()), a1.getId());
    }
    
    @Test
    public void updateAlbumTest() {
        Album alb = new Album();
        alb.setTitle("alb");
        albumDao.addAlbum(alb);
        em.getTransaction().commit();
        
        alb.setTitle("album");
        assertNotSame("Album title changed without persisting it.", albumDao.getAlbumById(alb.getId()).getTitle(), alb.getTitle());
        
        albumDao.updateAlbum(alb);
        em.getTransaction().commit();
        
        assertEquals("Album title didnt change.", albumDao.getAlbumById(alb.getId()).getTitle(), alb.getTitle());
        
    }
    
    @Test
    public void removeAlbumTest() {
        Album a1 = new Album();
        a1.setTitle("a1");
        Album a2 = new Album();
        a2.setTitle("a2");
        Album a3 = new Album();
        a3.setTitle("a3");
        albumDao.addAlbum(a1);
        albumDao.addAlbum(a2);
        albumDao.addAlbum(a3);
        em.getTransaction().commit();
        
        albumDao.removeAlbum(a2);
        albumDao.removeAlbum(a3);
        em.getTransaction().commit();
        
        assertEquals("Album 1 is removed when shouldn't.", PersistenceUtilHelper.isLoaded(a1), LoadState.LOADED);
        assertEquals("Album 2 is still loaded after removal.", PersistenceUtilHelper.isLoaded(a2), LoadState.NOT_LOADED);
        assertEquals("Album 3 is still loaded after removal.", PersistenceUtilHelper.isLoaded(a3), LoadState.NOT_LOADED);
    }
    
    @Test
    public void getAlbumsByNameTest() {
        Album a1 = new Album();
        a1.setTitle("album a1");
        Album a2 = new Album();
        a2.setTitle("album a2");
        
        albumDao.addAlbum(a1);
        albumDao.addAlbum(a2);
        em.getTransaction().commit();
        
        Set<Album> result = albumDao.getAlbumsByName("album a2");
        assertEquals("Set doesn't contain 1 result.", result.size(), 1);
        assertTrue("Result doesn't contain a2.", result.contains(a2));
        
        result = albumDao.getAlbumsByName("album a2");
        assertEquals("Set doesn't contain 2 results.", result.size(), 2);
        assertTrue("Result doesn't contain a1.", result.contains(a1));
        assertTrue("Result doesn't contain a2.", result.contains(a2));
    }
    
    @Test
    public void getAlbumBySongTest() {
        Song koneko = new Song();
        koneko.setTitle("わたしはこねこ");
        
        Song fall = new Song();
        fall.setTitle("Fall in the Dark");
        
        Set<Song> set1 = new HashSet<Song>();
        set1.add(koneko);
        Set<Song> set2 = new HashSet<Song>();
        set2.add(fall);
        
        Album emotional = new Album();
        emotional.setTitle("キセキ☆インパルス～emotional feedback");
        emotional.setSongs(set1);
        
        Album magicalCata = new Album();
        magicalCata.setTitle("マジコカタストロフィ");
        magicalCata.setSongs(set2);
        
        em.persist(koneko);
        em.persist(fall);
        albumDao.addAlbum(emotional);
        albumDao.addAlbum(magicalCata);
        em.getTransaction().commit();
        
        Set<Album> result = albumDao.getAlbumsBySong(koneko);
        result = albumDao.getAlbumsBySong(fall);
    }
    
    @Test
    public void getAlbumByMusicianTest() {
        Musician shibayan = new Musician();
        shibayan.setName("ShibayanRecords");
        
        Musician yana = new Musician();
        yana.setName("yana");
        
        Album emotional = new Album();
        emotional.setTitle("キセキ☆インパルス～emotional feedback");
        emotional.setMusician(shibayan);
        
        Album magicalCata = new Album();
        magicalCata.setTitle("マジコカタストロフィ");
        magicalCata.setMusician(yana);
        
        
        em.persist(shibayan);
        em.persist(yana);
        albumDao.addAlbum(emotional);
        albumDao.addAlbum(magicalCata);
        em.getTransaction().commit();
        
        Set<Album> result = albumDao.getAlbumsByMusician(shibayan);
        result = albumDao.getAlbumsByMusician(yana);
    }
    
    @Test
    public void getAlbumByDateTest() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.set(2009, 10, 11);
        cal2.set(2012, 12, 30);
        
        Album emotional = new Album();
        emotional.setTitle("キセキ☆インパルス～emotional feedback");
        emotional.setDateOfRelease(cal1.getTime());
        
        Album magicalCata = new Album();
        magicalCata.setTitle("マジコカタストロフィ");
        magicalCata.setDateOfRelease(cal2.getTime());
        
        albumDao.addAlbum(emotional);
        albumDao.addAlbum(magicalCata);
        em.getTransaction().commit();
        
        Set<Album> result = albumDao.getAlbumsByDate(cal1.getTime());
        result = albumDao.getAlbumsByDate(cal2.getTime());
    }

}
