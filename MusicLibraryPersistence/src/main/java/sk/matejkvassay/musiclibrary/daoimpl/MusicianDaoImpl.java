package sk.matejkvassay.musiclibrary.daoimpl;

import sk.matejkvassay.musiclibrary.daoimpl.exception.MusicianNameNullException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import sk.matejkvassay.musiclibrary.dao.MusicianDao;
import sk.matejkvassay.musiclibrary.entity.Album;
import sk.matejkvassay.musiclibrary.entity.Musician;
import sk.matejkvassay.musiclibrary.entity.Song;

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
    public Long addMusician(Musician musician) throws MusicianNameNullException {
        if (musician.getName() == null) {
            throw new MusicianNameNullException("Name of the musician cannot be NULL!");
        }
        em.persist(musician);
        return musician.getId();
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

    @Override
    public List<Musician> getMusicianByName(String name) {
        TypedQuery q = em.createQuery("SELECT m FROM Musician m WHERE LOWER(m.name) LIKE LOWER(:name)", Musician.class);
        q.setParameter("name", '%' + name + '%');
        return q.getResultList();  
    }
}
