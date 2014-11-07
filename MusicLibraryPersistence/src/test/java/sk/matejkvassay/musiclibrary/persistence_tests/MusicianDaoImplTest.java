/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.persistence_tests;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import sk.matejkvassay.musiclibrary.Dao.MusicianDao;
import sk.matejkvassay.musiclibrary.DaoContext;
import sk.matejkvassay.musiclibrary.DaoImpl.Exception.MusicianNameNullException;
import sk.matejkvassay.musiclibrary.Entity.Album;
import sk.matejkvassay.musiclibrary.Entity.Musician;
import sk.matejkvassay.musiclibrary.Entity.Song;

/**
 *
 * @author Marián Macik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MusicianDaoImplTest {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private MusicianDao musicianDao;
    @Inject
    private PlatformTransactionManager txManager;

    public MusicianDaoImplTest() {
    }

    /**
     * Test of addMusician method, of class MusicianDaoImpl.
     */
    @Test
    public void testAddMusician() throws Exception {
        System.out.println("addMusician");
        Musician musician1 = new Musician();
        musician1.setName("Musician 1");
        musician1.setBiography("Biography of musician 1");

        Musician musician2 = new Musician();
        musician2.setName("Musician 2");
        musician2.setBiography("Biography of musician 2");

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);

        musicianDao.addMusician(musician1);
        musicianDao.addMusician(musician2);

        txManager.commit(status);

        List<Musician> musicians = em.createQuery("SELECT m FROM Musician m ORDER BY m.name", Musician.class).getResultList();
        assertEquals(2, musicians.size());
        assertEquals("Musician 1", musicians.get(0).getName());
        assertEquals("Musician 2", musicians.get(1).getName());
        assertEquals("Biography of musician 1", musicians.get(0).getBiography());
        assertEquals("Biography of musician 2", musicians.get(1).getBiography());
    }

    @Test
    public void testAddMusicianWithNullName() throws Exception {
        System.out.println("addMusicianWithNullName");
        Musician musician1 = new Musician();

        TransactionStatus status = null;
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            musicianDao.addMusician(musician1);
            txManager.commit(status);
            fail("MusicianException was not thrown when musician's name was null");
        } catch (MusicianNameNullException ex) {
            txManager.rollback(status);
        }
    }

    @Test
    public void testAddMusicianWithNotUniqueName() throws Exception {
        System.out.println("addMusicianWithNotUniqueName");
        Musician musician1 = new Musician();
        musician1.setName("Musician 1");
        Musician musician2 = new Musician();
        musician2.setName("Musician 1");

        TransactionStatus status = null;
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            musicianDao.addMusician(musician1);
            musicianDao.addMusician(musician2);
            txManager.commit(status);
            fail("MusicianException was not thrown when musician's name was not unique");
        } catch (DataAccessException ex) {
            //commit thrown error so it has already cleaned the transaction as said in documentation
        }
    }

    /**
     * Test of removeMusician method, of class MusicianDaoImpl.
     */
    @Test
    public void testRemoveMusician() {
        System.out.println("removeMusician");
        Musician musician = new Musician();
        musician.setName("Musician to delete");

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);
        em.persist(musician);
        txManager.commit(status);

        List<Musician> musicians = em.createQuery("SELECT m FROM Musician m", Musician.class).getResultList();

        assertEquals(1, musicians.size());

        status = txManager.getTransaction(def);
        musician = em.createQuery("SELECT m FROM Musician m WHERE m.id = :id", Musician.class).setParameter("id", musician.getId()).getSingleResult();
        musicianDao.removeMusician(musician);
        txManager.commit(status);

        musicians = em.createQuery("SELECT m FROM Musician m", Musician.class).getResultList();

        assertEquals(0, musicians.size());
    }

    /**
     * Test of updateMusician method, of class MusicianDaoImpl.
     */
    @Test
    public void testUpdateMusician() throws Exception {
        System.out.println("updateMusician");

        Musician musician1 = new Musician();
        musician1.setName("Musician before update");

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);
        em.persist(musician1);
        txManager.commit(status);

        musician1 = em.find(Musician.class, musician1.getId());

        assertEquals(musician1.getName(), "Musician before update");

        status = txManager.getTransaction(def);
        musician1.setName("Musician after update");
        musicianDao.updateMusician(musician1);
        txManager.commit(status);

        musician1 = em.find(Musician.class, musician1.getId());

        assertEquals(musician1.getName(), "Musician after update");
    }

    @Test
    public void testUpdateMusicianWithNotUniqueName() throws Exception {
        System.out.println("addMusicianWithNotUniqueName");
        Musician musician1 = new Musician();
        musician1.setName("Musician 1");
        Musician musician2 = new Musician();
        musician2.setName("Musician 2");

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);

        musicianDao.addMusician(musician1);
        musicianDao.addMusician(musician2);

        txManager.commit(status);

        status = txManager.getTransaction(def);
        musician2.setName("Musician 1");
        musicianDao.updateMusician(musician2);

        try {
            txManager.commit(status);
            fail("Exception was not thrown when musician's name was not unique");
        } catch (DataAccessException ex) {
            //commit thrown error so it has already cleaned the transaction as said in documentation
        }
    }

    /**
     * Test of getAllMusicians method, of class MusicianDaoImpl.
     */
    @Test
    public void testGetAllMusicians() {
        System.out.println("getAllMusicians");

        Musician musician1 = new Musician();
        musician1.setName("Musician 1");
        musician1.setBiography("Biography of musician 1");

        Musician musician2 = new Musician();
        musician2.setName("Musician 2");
        musician2.setBiography("Biography of musician 2");

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);

        em.persist(musician1);
        em.persist(musician2);

        txManager.commit(status);

        List<Musician> musicians = musicianDao.getAllMusicians();
        assertEquals(2, musicians.size());
        assertEquals("Musician 1", musicians.get(0).getName());
        assertEquals("Musician 2", musicians.get(1).getName());
        assertEquals("Biography of musician 1", musicians.get(0).getBiography());
        assertEquals("Biography of musician 2", musicians.get(1).getBiography());
    }

    /**
     * Test of getMusicianById method, of class MusicianDaoImpl.
     */
    @Test
    public void testGetMusicianById() {
        System.out.println("getMusicianById");

        Musician musician1 = new Musician();
        musician1.setName("Musician 1");
        musician1.setBiography("Biography of musician 1");

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);

        em.persist(musician1);

        txManager.commit(status);

        musician1 = musicianDao.getMusicianById(musician1.getId());

        assertEquals("Musician 1", musician1.getName());
        assertEquals("Biography of musician 1", musician1.getBiography());
    }

    /**
     * Test of getMusicianByAlbum method, of class MusicianDaoImpl.
     */
    @Test
    public void testGetMusicianByAlbum() {
        System.out.println("getMusicianByAlbum");

        Set<Album> albums = new HashSet<>();
        Album album = new Album();
        album.setTitle("Album");
        albums.add(album);

        Musician musician1 = new Musician();
        musician1.setName("Musician 1");
        musician1.setAlbums(albums);
        album.setMusician(musician1);

        Musician musician2 = new Musician();
        musician2.setName("Musician 2");

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);

        em.persist(album);
        em.persist(musician1);
        em.persist(musician2);

        txManager.commit(status);

        musician1 = musicianDao.getMusicianByAlbum(album);

        assertEquals("Musician 1", musician1.getName());
    }

    /**
     * Test of getMusicianBySong method, of class MusicianDaoImpl.
     */
    @Test
    public void testGetMusicianBySong() {
        System.out.println("getMusicianBySong");

        Set<Song> songs = new HashSet<>();
        Song song = new Song();
        song.setTitle("Song 1");
        songs.add(song);

        Musician musician1 = new Musician();
        musician1.setName("Musician 1");
        musician1.setSongs(songs);
        song.setMusician(musician1);

        Musician musician2 = new Musician();
        musician2.setName("Musician 2");

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);

        em.persist(song);
        em.persist(musician1);
        em.persist(musician2);

        txManager.commit(status);

        musician1 = musicianDao.getMusicianBySong(song);

        assertEquals("Musician 1", musician1.getName());
    }
}
