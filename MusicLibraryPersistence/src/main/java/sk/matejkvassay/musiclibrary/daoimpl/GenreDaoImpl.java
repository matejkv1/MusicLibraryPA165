package sk.matejkvassay.musiclibrary.daoimpl;

import java.util.List;
import sk.matejkvassay.musiclibrary.dao.GenreDao;
import sk.matejkvassay.musiclibrary.entity.Genre;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import sk.matejkvassay.musiclibrary.entity.Song;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
@Repository
public class GenreDaoImpl implements GenreDao{
   
    @PersistenceContext
    private EntityManager em;
    
    public GenreDaoImpl(){}
    
    public GenreDaoImpl(EntityManager em){
        this.em=em;
    }
    @Override
    public void addGenre(Genre genre) {
        em.persist(genre);
    }

    @Override
    public void removeGenre(Genre genre) {
        em.remove(genre);
    }

    @Override
    public void updateGenre(Genre genre) {
        em.merge(genre);
    }
    
    @Override
    public Genre findGenreById(long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List getAllGenres() {
        return em.createQuery("SELECT g FROM  Genre g", Genre.class).getResultList();
    }

    @Override
    public Genre findGenreByName(String name) {
        TypedQuery query;
        query = em.createQuery("SELECT g FROM Genre g WHERE g.name=:name", Genre.class);
        query.setParameter("name", name);
        return (Genre) query.getSingleResult();
    }

    @Override
    public List<Song> getSongsOfGenre(Genre genre) {
        TypedQuery query;
        query = em.createQuery("SELECT s FROM Song s WHERE s.genre=:id", Song.class);
        query.setParameter("id", genre);
        return query.getResultList();
    }   
    
    @Override
    public Genre findGenreBySong(Song song) {
        TypedQuery query;
        query = em.createQuery("SELECT g FROM Genre g WHERE :song MEMBER OF g.songs", Genre.class);
        query.setParameter("song", song);
        return (Genre) query.getSingleResult();
    }
    
}
