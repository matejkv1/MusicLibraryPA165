
package sk.matejkvassay.musiclibrary.Dao;

import java.util.Date;
import java.util.List;
import sk.matejkvassay.musiclibrary.Entity.Album;
import sk.matejkvassay.musiclibrary.Entity.Musician;
import sk.matejkvassay.musiclibrary.Entity.Song;

/**
 *
 * @author Matej Bordáč
 */
public interface AlbumDao {
    /**
     * Adds an album entity into DB.
     * @param album 
     */
    public void addAlbum(Album album);
    
    /**
     * Removes an album entity from DB.
     * @param album 
     */
    public void removeAlbum(Album album);
    
    /**
     * Updates an album entity to an album in parameter,
     * if it already exists in DB.
     * @param album 
     */
    public void updateAlbum(Album album);
    
    /**
     * Searches for an album with ID from parameter.
     * @param id
     * @return Album instance with specified ID or null otherwise.
     */
    public Album getAlbumById(Long id);
    
    /**
     * Searches for albums having specified name in their title.
     * @param name
     * @return List of album instances.
     */
    public List<Album> getAlbumsByName(String name);
    
    /**
     * Searches for an album containing specified song.
     * @param song
     * @return Album instance or null otherwise.
     */
    public Album getAlbumBySong(Song song);
    
    /**
     * Searches for all albums by specified musician.
     * @param musician
     * @return List of album instances.
     */
    public List<Album> getAlbumsByMusician(Musician musician);
    
    /**
     * Searches for all albums created at specified date.
     * @param date
     * @return List of album instances.
     */
    public List<Album> getAlbumsByDate(Date date);
    
    /**
     * Searches for all albums.
     * @return List of album instances.
     */
    public List<Album> getAllAlbums();
    
    /**
     * Returns all songs in album.
     * @return List of all songs in album.
     */
    public List<Song> getAlbumSongs(Album album);
}
