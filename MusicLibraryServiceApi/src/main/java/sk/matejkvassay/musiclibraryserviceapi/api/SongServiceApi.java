/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibraryserviceapi.api;

import java.util.List;
import sk.matejkvassay.musiclibrary.Dto.AlbumDto;
import sk.matejkvassay.musiclibrary.Dto.GenreDto;
import sk.matejkvassay.musiclibrary.Dto.SongDto;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public interface SongServiceApi {
    
    /**
     * Get count of all songs.
     * @return Count of all songs.
     */
    public int getAllSongsCount();
    
    /**
     * Get count of songs with name containing given string.
     * @param name Given string.
     * @return Count of songs containing given string.
     */
    public int findSongsByNameCount(String name);
    
    /**
     * Get count of songs of given genre.
     * @param genre Given genre.
     * @return Songs of given genre.
     */
    public int getSongsByGenreCount(GenreDto genre);
    
    /**
     * Get sublist of ordered list of all songs.
     * @param from Limit from.
     * @param to Limit to.
     * @return Sublist of ordered list of all songs.
     */
    public List<SongDto> getAllSongsOrderedByNameLimit(int from, int to);
    
    /**
     * Get sublist of ordered list of songs of given album.
     * @param albumDto Given album.
     * @return Sublist of ordered list of songs of given album.
     */
    public List<SongDto> getSongsByAlbumOrderedByAlbumOrder(AlbumDto albumDto);
    
    /**
     * Get sublist of ordered list of songs with name containing given string.
     * @param name Given string.
     * @param from Limit from.
     * @param to Limit to.
     * @return Sublist of ordered list of songs with name containing given string.
     */
    public List<SongDto> findSongsByNameOrderedByNameLimit(String name, int from, int to);
    
    /**
     * Get sublist of ordered list of all songs of given genre.
     * @param genreDto Given genre.
     * @param from Limit from.
     * @param to Limit to.
     * @return Sublist of ordered list of all songs of given genre.
     */
    public List<SongDto> getSongsByGenreOrderedByNameLimit(GenreDto genreDto, int from, int to);
}
