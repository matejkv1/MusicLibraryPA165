package sk.matejkvassay.musiclibrary.Dao;

import java.util.List;
import sk.matejkvassay.musiclibrary.DaoImpl.Exception.MusicianNameNotUniqueException;
import sk.matejkvassay.musiclibrary.DaoImpl.Exception.MusicianNameNullException;
import sk.matejkvassay.musiclibrary.Entity.Album;
import sk.matejkvassay.musiclibrary.Entity.Musician;
import sk.matejkvassay.musiclibrary.Entity.Song;

/**
 * DAO for Musician class
 * @author Marián Macik
 */
public interface MusicianDao {
    
    /**
     * Stores new musician in DB
     * @param musician - musician to add
     * @throws MusicianNameNullException if name is null
     * @throws MusicianNameNotUniqueException if name is not unique in DB
     */
    public void addMusician(Musician musician) throws MusicianNameNullException;
    
    /**
     * Removes musician in DB
     * @param musician - musician to remove
     */
    public void removeMusician(Musician musician);
    
    /**
     * Updates musician in DB
     * @param musician - musician to update
     * @throws MusicianNameNullException if name is null
     */
    public void updateMusician(Musician musician) throws MusicianNameNullException;
    
    /**
     * Gets all musicians in DB
     * @return list of all musicians in DB
     */
    public List<Musician> getAllMusicians();
    
    /**
     * Finds musician by his ID
     * @param id id to find musician by
     * @return found musician
     */
    public Musician getMusicianById(Long id);
    
    /**
     * Finds musician by album
     * @param album album to find musician by
     * @return found musician
     */
    public Musician getMusicianByAlbum(Album album);
    
    /**
     * Finds musician by song
     * @param song song to find musician by
     * @return found musician
     */
    public Musician getMusicianBySong(Song song);
}
