package sk.matejkvassay.musiclibrary.DaoImpl;

import sk.matejkvassay.musiclibrary.DaoImpl.Exception.MusicianNameNullException;
import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;
import sk.matejkvassay.musiclibrary.Dao.MusicianDao;
import sk.matejkvassay.musiclibrary.Entity.Album;
import sk.matejkvassay.musiclibrary.Entity.Musician;
import sk.matejkvassay.musiclibrary.Entity.Song;

/**
 *
 * @author Marián Macik
 */
@Repository
public class MusicianDaoImpl implements MusicianDao {

    @PersistenceContext
    private EntityManager em;

    public MusicianDaoImpl() {
    }

    /**
     *
     * @param em EntityManager instance to work with
     */
    public MusicianDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addMusician(Musician musician) throws MusicianNameNullException {
        if (musician.getName() == null) {
            throw new MusicianNameNullException("Name of the musician cannot be NULL!");
        }
        em.persist(musician);
    }

    @Override
    public void removeMusician(Musician musician) {
        em.remove(musician);
    }

    @Override
    public void updateMusician(Musician musician) throws MusicianNameNullException {
        if (musician.getName() == null) {
            throw new MusicianNameNullException("Name of the musician cannot be NULL!");
        }
        em.merge(musician);
    }

    @Override
    public List<Musician> getAllMusicians() {
        return em.createQuery("SELECT m FROM Musician m", Musician.class).getResultList();
    }

    @Override
    public Musician getMusicianById(Long id) {
        return em.createQuery("SELECT m FROM Musician m WHERE m.id = :id", Musician.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public Musician getMusicianByAlbum(Album album) {
        return em.createQuery("SELECT m FROM Musician m WHERE :album MEMBER OF m.albums", Musician.class).setParameter("album", album).getSingleResult();
    }

    @Override
    public Musician getMusicianBySong(Song song) {
        return em.createQuery("SELECT m FROM Musician m WHERE :song MEMBER OF m.songs", Musician.class).setParameter("song", song).getSingleResult();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
