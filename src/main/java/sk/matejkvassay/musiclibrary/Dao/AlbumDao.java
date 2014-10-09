/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.Dao;

import java.util.Date;
import java.util.List;
import java.util.Set;
import sk.matejkvassay.musiclibrary.Entity.Album;
import sk.matejkvassay.musiclibrary.Entity.Musician;
import sk.matejkvassay.musiclibrary.Entity.Song;

/**
 *
 * @author Matej Bordáč
 */
public interface AlbumDao {
    // don't forget javadoc
    /**
     * 
     * @param album 
     */
    public void addAlbum(Album album);
    
    /**
     * 
     * @param album 
     */
    public void removeAlbum(Album album);
    
    /**
     * 
     * @param album 
     */
    public void updateAlbum(Album album);
    
    /**
     * 
     * @param id
     * @return 
     */
    public Album getAlbumById(Long id);
    
    /**
     * 
     * @param name
     * @return 
     */
    public List<Album> getAlbumsByName(String name);
    
    /**
     * 
     * @param song
     * @return 
     */
    public Album getAlbumBySong(Song song);
    
    /**
     * 
     * @param musician
     * @return 
     */
    public List<Album> getAlbumsByMusician(Musician musician);
    
    /**
     * 
     * @param date
     * @return 
     */
    public List<Album> getAlbumsByDate(Date date);
    
    /**
     * 
     * @return 
     */
    public List<Album> getAllAlbums();
}
