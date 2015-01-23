package sk.matejkvassay.musiclibrary.persistencetests;

import java.util.ArrayList;
import java.util.HashSet;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import sk.matejkvassay.musiclibrary.dao.SongDao;
import sk.matejkvassay.musiclibrary.DaoContext;
import sk.matejkvassay.musiclibrary.entity.Album;
import sk.matejkvassay.musiclibrary.entity.Genre;
import sk.matejkvassay.musiclibrary.entity.Musician;
import sk.matejkvassay.musiclibrary.entity.Song;

/**
 *
 * @author Horak
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SongDaoImplTest {
    
    @PersistenceContext
    private EntityManager em;
    @Inject
    private SongDao songDao;
    @Inject
    private PlatformTransactionManager txManager;

    private Song song1;
    private Song song2;
    private Song song3;
    private Song song4;

    private Album album1;
    private Album album2;

    private Genre genre1;
    private Genre genre2;

    private Musician musician1;
    private Musician musician2;

    public SongDaoImplTest() {

    }
    
    
    @Before
    public void setUp() {
        song1 = new Song();
        song1.setTitle("Song1 - album1 - genre1 - musician1 title");
        song1.setCommentary("Song1 comment");
		song1.setBitrate(100);
        song1.setPositionInAlbum(1);
		
        song2 = new Song();
        song2.setTitle("Song2 - album1 - genre1 - musician2 title");
        song2.setCommentary("Song2 comment");
        song2.setBitrate(100);
        song2.setPositionInAlbum(1);

        song3 = new Song();
        song3.setTitle("Song3 - album2 - genre1 - musician2 title");
        song3.setCommentary("Song3 comment");
        song3.setBitrate(160);
        song3.setPositionInAlbum(2);

        song4 = new Song();
        song4.setTitle("Song4 - album2 - genre2 - musician2 title");
        song4.setCommentary("Song4 comment");
        song4.setBitrate(160);
        song4.setPositionInAlbum(2);


        List<Song> listAlbum1 = new ArrayList<>();
        listAlbum1.add(song1);
        listAlbum1.add(song2);

        List<Song> listAlbum2 = new ArrayList<>();
        listAlbum2.add(song3);
        listAlbum2.add(song4);


        List<Song> listGenre1 = new ArrayList<>();
        listGenre1.add(song1);
        listGenre1.add(song2);
        listGenre1.add(song3);

        List<Song> listGenre2 = new ArrayList<>();
        listGenre2.add(song4);

        Set<Song> listMusician1 = new HashSet<>();
        listMusician1.add(song1);

        Set<Song> listMusician2 = new HashSet<>();
        listMusician2.add(song2);
        listMusician2.add(song3);
        listMusician2.add(song4);


        album1 = new Album();
        album1.setSongs(listAlbum1);
        album1.setTitle("Album1");
        song1.setAlbum(album1);
        song2.setAlbum(album1);

        album2 = new Album();
        album2.setSongs(listAlbum2);
        album2.setTitle("Album2");
        song3.setAlbum(album2);
        song4.setAlbum(album2);


        genre1 = new Genre();
        genre1.setSongs(listGenre1);
        genre1.setName("Genre1");
        song1.setGenre(genre1);
        song2.setGenre(genre1);
        song3.setGenre(genre1);

        genre2 = new Genre();
        genre2.setSongs(listGenre2);
        genre2.setName("Genre2");
        song4.setGenre(genre2);


        musician1 = new Musician();
        musician1.setSongs(listMusician1);
        musician1.setName("Musician1");
        song1.setMusician(musician1);

        musician2 = new Musician();
        musician2.setSongs(listMusician2);
        musician2.setName("Musician2");
        song2.setMusician(musician2);
        song3.setMusician(musician2);
        song4.setMusician(musician2);

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);

        em.persist(song1);
        em.persist(song2);
        em.persist(song3);
        em.persist(song4);

        em.persist(album1);
        em.persist(album2);

        em.persist(genre1);
        em.persist(genre2);

        em.persist(musician1);
        em.persist(musician2);
        
        txManager.commit(status);
    }
    
    @After
    public void tearDown() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);
        
        em.createQuery("DELETE FROM Song").executeUpdate();
        em.createQuery("DELETE FROM Album").executeUpdate();
        em.createQuery("DELETE FROM Genre").executeUpdate();
        em.createQuery("DELETE FROM Musician").executeUpdate();
        
        txManager.commit(status);
    }

    @Test
    public void testAddSong() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);

        Song s = new Song();
        s.setTitle("Song5");
        songDao.addSong(s);
        
        txManager.commit(status);
        
        List<Song> songs = em.createQuery("SELECT s FROM Song s ORDER BY s.title", Song.class).getResultList();
        assertEquals(5, songs.size());
        assertEquals("Song5", songs.get(4).getTitle());
        
        status = txManager.getTransaction(def);
        
        s = em.find(Song.class, s.getId());
        em.remove(s);
        
        txManager.commit(status);
    }
	
    @Test
    public void testRemoveSong() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);
        
        Song s = new Song();
        s.setTitle("Song5");
        em.persist(s);
        
        txManager.commit(status);
        
        List<Song> songs = em.createQuery("SELECT s FROM Song s ORDER BY s.title", Song.class).getResultList();
        assertEquals(5, songs.size());
        assertEquals("Song5", songs.get(4).getTitle());
        
        status = txManager.getTransaction(def);

        s = em.find(Song.class, s.getId());
        songDao.removeSong(s);
        
        txManager.commit(status);
        
        songs = em.createQuery("SELECT s FROM Song s ORDER BY s.title", Song.class).getResultList();
        assertEquals(4, songs.size());
    }
	
    @Test
    public void testUpdateSong() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);

        Song s = new Song();
        s.setTitle("Song5");
        em.persist(s);
        
        txManager.commit(status);
        
        List<Song> songs = em.createQuery("SELECT s FROM Song s ORDER BY s.title", Song.class).getResultList();
        assertEquals(5, songs.size());
        assertEquals("Song5", songs.get(4).getTitle());

        s = em.find(Song.class, s.getId());
        s.setTitle("Song6");
        
        status = txManager.getTransaction(def);
        songDao.updateSong(s);
        txManager.commit(status);
        
        songs = em.createQuery("SELECT s FROM Song s ORDER BY s.title", Song.class).getResultList();
        System.out.println("****  " + songs);
        assertEquals(5, songs.size());
        assertEquals("Song6", songs.get(4).getTitle());
        
        status = txManager.getTransaction(def);

        s = em.find(Song.class, s.getId());
        em.remove(s);
        
        txManager.commit(status);
    }

    @Test
    public void testGetAllSongs() {
        List<Song> songs = songDao.getAllSongs();
        assertEquals(4, songs.size());
        assertEquals("Song1 - album1 - genre1 - musician1 title", songs.get(0).getTitle());
        assertEquals("Song2 - album1 - genre1 - musician2 title", songs.get(1).getTitle());
        assertEquals("Song3 - album2 - genre1 - musician2 title", songs.get(2).getTitle());
        assertEquals("Song4 - album2 - genre2 - musician2 title", songs.get(3).getTitle());
    }

    @Test
    public void testGetSongsByName() {
        List<Song> songs = songDao.getSongsByName("album1");
        assertEquals(2, songs.size());
        assertEquals(true, songs.get(0).getTitle().contains("album1"));
        assertEquals(true, songs.get(1).getTitle().contains("album1"));
        assertEquals(false, songs.get(0).getTitle().contains("album2"));
        assertEquals(false, songs.get(1).getTitle().contains("album2"));
    }
	
    @Test
    public void testGetSongById() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);

        Song s = new Song();
        s.setTitle("IdSong");
        em.persist(s);
        
        txManager.commit(status);
        
        s = songDao.getSongById(s.getId());

        assertEquals("IdSong", s.getTitle());
        
        status = txManager.getTransaction(def);

        s = em.find(Song.class, s.getId());
        em.remove(s);
        
        txManager.commit(status);
    }
	
    @Test
    public void testGetSongsByAlbum() {
        List<Song> songs = songDao.getSongsByAlbum(album1);
        assertNotNull(songs);
        assertEquals(2, songs.size());

        songs = null;

        songs = songDao.getSongsByAlbum(album2);
        assertNotNull(songs);
        assertEquals(2, songs.size());
    }
	
    @Test
    public void testGetSongsByGenre() {
        List<Song> songs = songDao.getSongsByGenre(genre1);
        assertNotNull(songs);
        assertEquals(3, songs.size());

        songs = null;

        songs = songDao.getSongsByGenre(genre2);
        assertNotNull(songs);
        assertEquals(1, songs.size());
    }
	
    @Test
    public void testGetSongsByMusician() {
        List<Song> songs = songDao.getSongsByMusician(musician1);
        assertNotNull(songs);
        assertEquals(1, songs.size());

        songs = null;

        songs = songDao.getSongsByMusician(musician2);
        assertNotNull(songs);
        assertEquals(3, songs.size());
    }
}
