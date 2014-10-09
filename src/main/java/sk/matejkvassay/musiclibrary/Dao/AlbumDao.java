/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.Dao;

import java.util.Date;
import java.util.Set;
import sk.matejkvassay.musiclibrary.Entity.Album;
import sk.matejkvassay.musiclibrary.Entity.Musician;
import sk.matejkvassay.musiclibrary.Entity.Song;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public interface AlbumDao {
    // don't forget javadoc
    public void addAlbum(Album album);
    public void removeAlbum(Album album);
    public void updateAlbum(Album album);
    public Album getAlbumById(Long id);
    public Set<Album> getAlbumsByName(String name);
    public Set<Album> getAlbumsBySong(Song song);
    public Set<Album> getAlbumsByMusician(Musician musician);
    public Set<Album> getAlbumsByDate(Date date);
    public Set<Album> getAllAlbums();
}
