
package sk.matejkvassay.musiclibrary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
 * @author Matej Bordáč
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
        
        Album alb2 = new Album();
        alb2.setTitle("alb2");
        albumDao.addAlbum(alb2);
        
        em.getTransaction().commit();
        
        List<Album> result = em.createQuery("SELECT a FROM Album a", Album.class).getResultList();
        
        assertEquals("All albums are not loaded.", result.size(), 2);
        
        em.getTransaction().begin();
        em.remove(alb);
        em.remove(alb2);
        em.getTransaction().commit();
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
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        em.persist(a4);
        em.getTransaction().commit();
        
        List<Album> result = albumDao.getAllAlbums();
        
        assertNotNull("getAllAlbums() returned null, should be 4.", result);
        assertEquals("getAllAlbums() didn't return 4 albums as expected.", result.size(), 4);
        assertTrue("getAllAlbums() didn't include a1.", result.contains(a1));
        assertTrue("getAllAlbums() didn't include a2.", result.contains(a2));
        assertTrue("getAllAlbums() didn't include a3.", result.contains(a3));
        assertTrue("getAllAlbums() didn't include a4.", result.contains(a4));
        
        em.getTransaction().begin();
        em.remove(a1);
        em.remove(a2);
        em.remove(a3);
        em.remove(a4);
        em.getTransaction().commit();
    }
    
    @Test
    public void getAlbumByIdTest() {
        Album a1 = new Album();
        a1.setTitle("a1");
        Album a2 = new Album();
        a2.setTitle("a2");
        Album a3 = new Album();
        a3.setTitle("a3");
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        em.getTransaction().commit();
        
        assertEquals("Didn't return same ID album 2.", albumDao.getAlbumById(a2.getId()), a2);
        assertEquals("Didn't return same ID album 3.", albumDao.getAlbumById(a3.getId()), a3);
        assertEquals("Didn't return same ID album 1.", albumDao.getAlbumById(a1.getId()), a1);
        
        em.getTransaction().begin();
        em.remove(a1);
        em.remove(a2);
        em.remove(a3);
        em.getTransaction().commit();
    }
    
    @Test
    public void updateAlbumTest() {
        Album alb = new Album();
        alb.setTitle("alb");
        em.persist(alb);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        alb.setTitle("album");
        albumDao.updateAlbum(alb);
        em.getTransaction().commit();
        
        Album res = em.createQuery("SELECT a FROM Album a", Album.class).getResultList().get(0);
        assertEquals("Album title didnt change.", res.getTitle(), alb.getTitle());
        
        em.getTransaction().begin();
        em.remove(alb);
        em.getTransaction().commit();
    }
    
    @Test
    public void removeAlbumTest() {
        Album a1 = new Album();
        a1.setTitle("a1");
        Album a2 = new Album();
        a2.setTitle("a2");
        Album a3 = new Album();
        a3.setTitle("a3");
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        albumDao.removeAlbum(a2);
        albumDao.removeAlbum(a3);
        em.getTransaction().commit();
        
        List<Album> result = em.createQuery("SELECT a FROM Album a", Album.class).getResultList();
        assertTrue("Album 1 is removed when shouldn't.", result.contains(a1));
        assertFalse("Album 2 is still loaded after removal.", result.contains(a2));
        assertFalse("Album 3 is still loaded after removal.", result.contains(a3));
        
        em.getTransaction().begin();
        em.remove(a1);
        em.getTransaction().commit();
    }
    
    @Test
    public void getAlbumsByNameTest() {
        Album a1 = new Album();
        a1.setTitle("album a1");
        Album a2 = new Album();
        a2.setTitle("album a2");
        
        em.persist(a1);
        em.persist(a2);
        em.getTransaction().commit();
        
        List<Album> result = albumDao.getAlbumsByName("album a2");
        assertNotNull("Returned null", result);
        assertEquals("Set doesn't contain 1 result.", result.size(), 1);
        assertTrue("Result doesn't contain a2.", result.contains(a2));
        
        result = albumDao.getAlbumsByName("a1");
        assertNotNull("Returned null", result);
        assertEquals("Set doesn't contain 1 result.", result.size(), 1);
        assertTrue("Result doesn't contain a1.", result.contains(a1));
        
        result = albumDao.getAlbumsByName("album");
        assertNotNull("Returned null", result);
        assertEquals("Set doesn't contain 2 results.", result.size(), 2);
        assertTrue("Result doesn't contain a1.", result.contains(a1));
        assertTrue("Result doesn't contain a2.", result.contains(a2));
        
        em.getTransaction().begin();
        em.remove(a1);
        em.remove(a2);
        em.getTransaction().commit();
    }
    
    @Test
    public void getAlbumBySongTest() {
        Song koneko = new Song();
        koneko.setTitle("わたしはこねこ");
        
        Song fall = new Song();
        fall.setTitle("Fall in the Dark");
        
        List<Song> set1 = new ArrayList<Song>();
        set1.add(koneko);
        List<Song> set2 = new ArrayList<Song>();
        set2.add(fall);
        
        Album emotional = new Album();
        emotional.setTitle("キセキ☆インパルス～emotional feedback");
        emotional.setSongs(set1);
        
        Album magicalCata = new Album();
        magicalCata.setTitle("マジコカタストロフィ");
        magicalCata.setSongs(set2);
        
        koneko.setAlbum(emotional);
        fall.setAlbum(magicalCata);
        
        em.persist(koneko);
        em.persist(fall);
        em.persist(emotional);
        em.persist(magicalCata);
        em.getTransaction().commit();
        
        Album result = albumDao.getAlbumBySong(koneko);
        assertNotNull("Returned null", result);
        assertEquals("Returned albums don't match", result.getId(), emotional.getId());
        
        result = albumDao.getAlbumBySong(fall);
        assertNotNull("Returned null", result);
        assertEquals("Returned albums don't match", result.getId(), magicalCata.getId());
        
        em.getTransaction().begin();
        em.remove(koneko);
        em.remove(fall);
        em.remove(emotional);
        em.remove(magicalCata);
        em.getTransaction().commit();
    }
    
    @Test
    public void getAlbumsByMusicianTest() {
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
        em.persist(emotional);
        em.persist(magicalCata);
        em.getTransaction().commit();
        
        List<Album> result = albumDao.getAlbumsByMusician(shibayan);
        assertNotNull("Returned null", result);
        assertTrue("Result doesn't contain proper album.", result.contains(emotional));
        assertEquals("Result is not of the correct size.", result.size(), 1);
        
        result = albumDao.getAlbumsByMusician(yana);
        assertNotNull("Returned null", result);
        assertTrue("Result doesn't contain proper album.", result.contains(magicalCata));
        assertEquals("Result is not of the correct size.", result.size(), 1);
        
        em.getTransaction().begin();
        em.remove(emotional);
        em.remove(magicalCata);
        em.remove(shibayan);
        em.remove(yana);
        em.getTransaction().commit();
    }
    
    @Test
    public void getAlbumsByDateTest() {
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
        
        em.persist(emotional);
        em.persist(magicalCata);
        em.getTransaction().commit();
        
        List<Album> result = albumDao.getAlbumsByDate(cal1.getTime());
        assertNotNull("Returned null", result);
        assertTrue("Result doesn't contain proper album.", result.contains(emotional));
        assertEquals("Result is not of the correct size.", result.size(), 1);
        
        result = albumDao.getAlbumsByDate(cal2.getTime());
        assertNotNull("Returned null", result);
        assertTrue("Result doesn't contain proper album.", result.contains(magicalCata));
        assertEquals("Result is not of the correct size.", result.size(), 1);
        
        em.getTransaction().begin();
        em.remove(emotional);
        em.remove(magicalCata);
        em.getTransaction().commit();
    }

}
