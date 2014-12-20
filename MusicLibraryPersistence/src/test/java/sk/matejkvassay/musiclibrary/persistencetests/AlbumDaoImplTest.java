
package sk.matejkvassay.musiclibrary.persistencetests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import sk.matejkvassay.musiclibrary.dao.AlbumDao;
import sk.matejkvassay.musiclibrary.DaoContext;
import sk.matejkvassay.musiclibrary.entity.Album;
import sk.matejkvassay.musiclibrary.entity.Musician;
import sk.matejkvassay.musiclibrary.entity.Song;


/**
 *
 * @author Matej Bordáč
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AlbumDaoImplTest {
    
    @PersistenceContext
    private EntityManager em;
    @Inject
    AlbumDao albumDao;
    @Inject
    private PlatformTransactionManager txManager;
    
    public AlbumDaoImplTest() {
    }
    
    @Test
    public void addAlbumTest() {
        Album alb = new Album();
        alb.setTitle("alb");
        
        Album alb2 = new Album();
        alb2.setTitle("alb2");
        
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);
        
        albumDao.addAlbum(alb);
        albumDao.addAlbum(alb2);
        
        txManager.commit(status);
        
        List<Album> result = em.createQuery("SELECT a FROM Album a", Album.class).getResultList();
        
        assertEquals("All albums are not loaded.", result.size(), 2);
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
        
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        em.persist(a4);
        txManager.commit(status);
        
        List<Album> result = albumDao.getAllAlbums();
        
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
        
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        txManager.commit(status);
        
        assertEquals("Didn't return same ID album 2.", albumDao.getAlbumById(a2.getId()), a2);
        assertEquals("Didn't return same ID album 3.", albumDao.getAlbumById(a3.getId()), a3);
        assertEquals("Didn't return same ID album 1.", albumDao.getAlbumById(a1.getId()), a1);
    }
    
    @Test
    public void updateAlbumTest() {
        Album alb = new Album();
        alb.setTitle("alb");
        
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);
        em.persist(alb);
        txManager.commit(status);
        
        alb = em.find(Album.class, alb.getId());
        alb.setTitle("album");
        
        status = txManager.getTransaction(def);
        albumDao.updateAlbum(alb);
        txManager.commit(status);
        
        alb = em.find(Album.class, alb.getId());
        assertEquals("Album title didnt change.", alb.getTitle(), "album");
    }
    
    @Test
    public void removeAlbumTest() {
        Album a1 = new Album();
        a1.setTitle("a1");
        Album a2 = new Album();
        a2.setTitle("a2");
        Album a3 = new Album();
        a3.setTitle("a3");
        
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        txManager.commit(status);
        
        status = txManager.getTransaction(def);
        a2 = em.createQuery("SELECT a FROM Album a WHERE a.id = :id", Album.class).setParameter("id", a2.getId()).getSingleResult();
        a3 = em.createQuery("SELECT a FROM Album a WHERE a.id = :id", Album.class).setParameter("id", a3.getId()).getSingleResult();
        albumDao.removeAlbum(a2);
        albumDao.removeAlbum(a3);
        txManager.commit(status);
        
        List<Album> result = em.createQuery("SELECT a FROM Album a", Album.class).getResultList();
        assertTrue("Album 1 is removed when shouldn't.", result.contains(a1));
        assertFalse("Album 2 is still loaded after removal.", result.contains(a2));
        assertFalse("Album 3 is still loaded after removal.", result.contains(a3));
    }
    
    @Test
    public void getAlbumsByNameTest() {
        Album a1 = new Album();
        a1.setTitle("album a1");
        Album a2 = new Album();
        a2.setTitle("album a2");
        
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);
        em.persist(a1);
        em.persist(a2);
        txManager.commit(status);
        
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
        
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);
        em.persist(koneko);
        em.persist(fall);
        em.persist(emotional);
        em.persist(magicalCata);
        txManager.commit(status);
        
        Album result = albumDao.getAlbumBySong(koneko);
        assertNotNull("Returned null", result);
        assertEquals("Returned albums don't match", result.getId(), emotional.getId());
        
        result = albumDao.getAlbumBySong(fall);
        assertNotNull("Returned null", result);
        assertEquals("Returned albums don't match", result.getId(), magicalCata.getId());
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
        
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);
        em.persist(shibayan);
        em.persist(yana);
        em.persist(emotional);
        em.persist(magicalCata);
        txManager.commit(status);
        
        List<Album> result = albumDao.getAlbumsByMusician(shibayan);
        assertNotNull("Returned null", result);
        assertTrue("Result doesn't contain proper album.", result.contains(emotional));
        assertEquals("Result is not of the correct size.", result.size(), 1);
        
        result = albumDao.getAlbumsByMusician(yana);
        assertNotNull("Returned null", result);
        assertTrue("Result doesn't contain proper album.", result.contains(magicalCata));
        assertEquals("Result is not of the correct size.", result.size(), 1);
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
        
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);
        em.persist(emotional);
        em.persist(magicalCata);
        txManager.commit(status);
        
        List<Album> result = albumDao.getAlbumsByDate(cal1.getTime());
        assertNotNull("Returned null", result);
        assertTrue("Result doesn't contain proper album.", result.contains(emotional));
        assertEquals("Result is not of the correct size.", result.size(), 1);
        
        result = albumDao.getAlbumsByDate(cal2.getTime());
        assertNotNull("Returned null", result);
        assertTrue("Result doesn't contain proper album.", result.contains(magicalCata));
        assertEquals("Result is not of the correct size.", result.size(), 1);
    }
    
    @Test
    public void getAllSongsTest() {
        Song koneko = new Song();
        koneko.setTitle("わたしはこねこ");
        
        Song fall = new Song();
        fall.setTitle("Fall in the Dark");
        
        List<Song> set1 = new ArrayList<>();
        set1.add(koneko);
        set1.add(fall);
        
        Album emotional = new Album();
        emotional.setTitle("キセキ☆インパルス～emotional feedback");
        emotional.setSongs(set1);
        
        Album magicalCata = new Album();
        magicalCata.setTitle("マジコカタストロフィ");
        
        koneko.setAlbum(emotional);
        fall.setAlbum(emotional);
        
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);
        em.persist(koneko);
        em.persist(fall);
        em.persist(emotional);
        em.persist(magicalCata);
        txManager.commit(status);
        
        List<Song> result = albumDao.getAlbumSongs(emotional);
        assertNotNull("Returned null", result);
        assertEquals("Returned album size doesn't match.", result.size(), 2);
        assertTrue("Returned songs list varies.", result.containsAll(emotional.getSongs()));
    }

}
