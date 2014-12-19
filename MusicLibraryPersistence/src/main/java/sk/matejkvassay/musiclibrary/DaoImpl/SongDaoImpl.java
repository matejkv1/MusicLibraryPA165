package sk.matejkvassay.musiclibrary.DaoImpl;

import java.util.List;
import sk.matejkvassay.musiclibrary.Dao.SongDao;
import sk.matejkvassay.musiclibrary.Entity.Song;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import sk.matejkvassay.musiclibrary.Entity.Album;
import sk.matejkvassay.musiclibrary.Entity.Genre;
import sk.matejkvassay.musiclibrary.Entity.Musician;

/**
 *
 * @author Horak
 */
@Repository
public class SongDaoImpl implements SongDao {

    @PersistenceContext
    private EntityManager em;

    public SongDaoImpl() {
    }

    public SongDaoImpl(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addSong(Song song) {
        em.persist(song);
    }

    @Override
    public void removeSong(Song song) {
        em.remove(song);
    }

    @Override
    public void updateSong(Song song) {
        Song loadedSong = getSongById(song.getId());
        if (loadedSong != null) {
            em.merge(song);
        }
    }

    @Override
    public List<Song> getAllSongs() {
        return em.createQuery("SELECT s FROM Song s", Song.class).getResultList();
    }

    @Override
    public List<Song> getSongsByName(String nameOfSong) {
        TypedQuery q = em.createQuery("SELECT s FROM Song s WHERE s.title LIKE :nameOfSong", Song.class);
        q.setParameter("nameOfSong", '%' + nameOfSong + '%');
        return q.getResultList();
    }

    @Override
    public Song getSongById(Long id) {
        return em.find(Song.class, id);
    }

    @Override
    public List<Song> getSongsByAlbum(Album album) {
        TypedQuery q = em.createQuery("SELECT s FROM Song s WHERE s.album = :album", Song.class);
        q.setParameter("album", album);
        return q.getResultList();
    }

    @Override
    public List<Song> getSongsByMusician(Musician musician) {
        TypedQuery q = em.createQuery("SELECT s FROM Song s WHERE s.musician = :musician", Song.class);
        q.setParameter("musician", musician);
        return q.getResultList();
    }

    @Override
    public List<Song> getSongsByGenre(Genre genre) {
        TypedQuery q = em.createQuery("SELECT s FROM Song s WHERE s.genre = :genre", Song.class);
        q.setParameter("genre", genre);
        return q.getResultList();
    }

}
