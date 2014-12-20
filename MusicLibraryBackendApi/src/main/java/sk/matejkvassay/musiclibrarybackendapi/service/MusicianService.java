package sk.matejkvassay.musiclibrarybackendapi.service;

import java.util.List;
import sk.matejkvassay.musiclibrary.daoimpl.exception.MusicianNameNullException;
import sk.matejkvassay.musiclibrarybackendapi.dto.AlbumDto;
import sk.matejkvassay.musiclibrarybackendapi.dto.MusicianDto;
import sk.matejkvassay.musiclibrarybackendapi.dto.SongDto;

/**
 * Service class for Musician
 * @author Marián Macik
 */
public interface MusicianService {
    
    /**
     * Stores new musician
     * @param musician - musician to add
     */
    public Long addMusician(MusicianDto musician) throws MusicianNameNullException;
    
    /**
     * Removes musician
     * @param musician - musician to remove
     */
    public void removeMusician(MusicianDto musician);
    
    /**
     * Updates musician
     * @param musician - musician to update
     */
    public void updateMusician(MusicianDto musician) throws MusicianNameNullException;
    
    /**
     * Gets all musicians
     * @return list of all musicians in DB
     */
    public List<MusicianDto> getAllMusicians();
    
    /**
     * Finds musician by his ID
     * @param id id to find musician by
     * @return found musician
     */
    public MusicianDto getMusicianById(Long id);
    
    /**
     * Finds musician by album
     * @param album album to find musician by
     * @return found musician
     */
    public MusicianDto getMusicianByAlbum(AlbumDto album);
    
    /**
     * Finds musician by song
     * @param song song to find musician by
     * @return found musician
     */
    public MusicianDto getMusicianBySong(SongDto song);
    
}
