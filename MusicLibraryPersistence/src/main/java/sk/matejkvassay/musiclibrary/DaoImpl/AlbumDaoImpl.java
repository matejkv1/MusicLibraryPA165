
package sk.matejkvassay.musiclibrary.DaoImpl;

import java.util.Date;
import java.util.List;
import sk.matejkvassay.musiclibrary.Dao.AlbumDao;
import sk.matejkvassay.musiclibrary.Entity.Album;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import sk.matejkvassay.musiclibrary.Entity.Musician;
import sk.matejkvassay.musiclibrary.Entity.Song;

/**
 *
 * @author Matej Bordáč
 */
@Repository
public class AlbumDaoImpl implements AlbumDao {
    
    @PersistenceContext
    private EntityManager em;
    
    public AlbumDaoImpl() {
    }
    
    /**
     * 
     * @param em EntityManager instance to work with.
     */
    public AlbumDaoImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public void addAlbum(Album album) {
        em.persist(album);
    }
    
    @Override
    public void removeAlbum(Album album) {
        em.remove(album);
    }
    
    @Override
    public void updateAlbum(Album album) {
        em.merge(album);
    }
    
    @Override
    public Album getAlbumById(Long id) {
        return em.find(Album.class, id);
    }
    
    @Override
    public List<Album> getAlbumsByName(String name) {
        TypedQuery q = em.createQuery("SELECT a FROM Album a WHERE a.title LIKE :name", Album.class);
        q.setParameter("name", '%' + name + '%');
        return q.getResultList();
    }
    
    @Override
    public Album getAlbumBySong(Song song) {
        TypedQuery q = em.createQuery("SELECT a FROM Album a WHERE :song MEMBER OF a.songs", Album.class);
        q.setParameter("song", song);
        List<Album> result = q.getResultList();
        
        if (result.size() != 1) return null;
        else return (Album)result.get(0);
    }
    
    @Override
    public List<Album> getAlbumsByMusician(Musician musician) {
        TypedQuery q = em.createQuery("SELECT a FROM Album a WHERE a.musician = :musician", Album.class);
        q.setParameter("musician", musician);
        return q.getResultList();
    }
    
    @Override
    public List<Album> getAlbumsByDate(Date date) {
        TypedQuery q = em.createQuery("SELECT a FROM Album a WHERE a.dateOfRelease = :date", Album.class);
        q.setParameter("date", date);
        return q.getResultList();
    }
    
    @Override
    public List<Album> getAllAlbums() {
        return em.createQuery("SELECT a FROM Album a", Album.class).getResultList();
    }
    
    @Override
    public List<Song> getAlbumSongs(Album album) {
        TypedQuery q = em.createQuery("SELECT s FROM Song s WHERE s.album = :id", Song.class);
        q.setParameter("id", album);
        return q.getResultList();
    }

}
