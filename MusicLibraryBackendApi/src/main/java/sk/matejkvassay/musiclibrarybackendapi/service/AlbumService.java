
package sk.matejkvassay.musiclibrarybackendapi.service;

import java.util.Date;
import java.util.List;
import sk.matejkvassay.musiclibrarybackendapi.dto.AlbumDto;
import sk.matejkvassay.musiclibrarybackendapi.dto.MusicianDto;
import sk.matejkvassay.musiclibrarybackendapi.dto.SongDto;

/**
 * Service class for album
 * @author Matej Bordáč
 */
public interface AlbumService {
    
    /**
     * Stores new album
     * @param album new album to add
     */
    public void addAlbum(AlbumDto album);
    
    /**
     * Removes album
     * @param album album to remove
     */
    public void removeAlbum(AlbumDto album);
    
    /**
     * Updates existing album
     * @param album to update
     */
    public void updateAlbum(AlbumDto album);
    
    /**
     * Finds album with specific id
     * @param id album id to search for
     * @return album with specified id
     */
    public AlbumDto getAlbumById(Long id);
    
    /**
     * Finds album containing name in title
     * @param name name of album to search for
     * @return list of albums with specified name
     */
    public List<AlbumDto> getAlbumsByName(String name);
    
    /**
     * Get album containing song
     * @param song song to get album for
     * @return album containing specified song
     */
    public AlbumDto getAlbumBySong(SongDto song);
    
    /**
     * Get all albums by musician
     * @param musician musician to get albums for
     * @return list of all albums by specified musician
     */
    public List<AlbumDto> getAlbumsByMusician(MusicianDto musician);
    
    /**
     * Get all albums created on date
     * @param date date to get albums for
     * @return list of all albums created on specified date
     */
    public List<AlbumDto> getAlbumsByDate(Date date);
    
    /**
     * Returns all stored albums
     * @return list of all albums
     */
    public List<AlbumDto> getAllAlbums();
    
}
