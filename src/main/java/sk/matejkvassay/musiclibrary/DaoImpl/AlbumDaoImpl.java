/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.DaoImpl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import sk.matejkvassay.musiclibrary.Dao.AlbumDao;
import sk.matejkvassay.musiclibrary.Entity.Album;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import sk.matejkvassay.musiclibrary.Entity.Musician;
import sk.matejkvassay.musiclibrary.Entity.Song;

/**
 *
 * @author Matej Bordáč
 */
public class AlbumDaoImpl implements AlbumDao {
    
    private EntityManager em;
    
    public AlbumDaoImpl(EntityManager em){
        this.em = em;
    }

    public void addAlbum(Album album) {
        em.persist(album);
    }
    
    public void removeAlbum(Album album) {
        em.remove(album);
    }
    
    public void updateAlbum(Album album) {
        Album a = getAlbumById(album.getId());
        if (a != null) em.persist(album);
    }
    
    public Album getAlbumById(Long id) {
        return em.find(Album.class, id);
    }
    
    public List<Album> getAlbumsByName(String name) {
        TypedQuery q = em.createQuery("SELECT a FROM Album a WHERE a.title LIKE :name", Album.class);
        q.setParameter("name", '%' + name + '%');
        return q.getResultList();
    }
    
    public Album getAlbumBySong(Song song) {
        TypedQuery q = em.createQuery("SELECT a FROM Album a WHERE :song MEMBER OF a.songs", Album.class);
        q.setParameter("song", song);
        List<Album> result = q.getResultList();
        
        if (result.size() != 1) return null;
        else return (Album)result.get(0);
    }
    
    public List<Album> getAlbumsByMusician(Musician musician) {
        TypedQuery q = em.createQuery("SELECT a FROM Album a WHERE a.musician = :musician", Album.class);
        q.setParameter("musician", musician);
        return q.getResultList();
    }
    
    public List<Album> getAlbumsByDate(Date date) {
        TypedQuery q = em.createQuery("SELECT a FROM Album a WHERE a.dateOfRelease = :date", Album.class);
        q.setParameter("date", date);
        return q.getResultList();
    }
    
    public List<Album> getAllAlbums() {
        return em.createQuery("SELECT a FROM Album a", Album.class).getResultList();
    }

}
