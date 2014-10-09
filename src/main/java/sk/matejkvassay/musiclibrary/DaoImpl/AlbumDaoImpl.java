/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.DaoImpl;

import java.util.Date;
import java.util.Set;
import sk.matejkvassay.musiclibrary.Dao.AlbumDao;
import sk.matejkvassay.musiclibrary.Entity.Album;
import javax.persistence.EntityManager;
import sk.matejkvassay.musiclibrary.Entity.Musician;
import sk.matejkvassay.musiclibrary.Entity.Song;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public class AlbumDaoImpl implements AlbumDao{
    
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
    
    public Set<Album> getAlbumsByName(String name) {
        return null;
    }
    
    public Set<Album> getAlbumsBySong(Song song) {
        return null;
    }
    
    public Set<Album> getAlbumsByMusician(Musician musician) {
        return null;
    }
    
    public Set<Album> getAlbumsByDate(Date date) {
        return null;
    }
    
    public Set<Album> getAllAlbums() {
        return null;
    }
    

}
