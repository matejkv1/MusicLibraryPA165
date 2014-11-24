/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.serviceInterface;

import java.util.List;
import sk.matejkvassay.musiclibrary.Dto.AlbumDto;
import sk.matejkvassay.musiclibrary.Dto.MusicianDto;
import sk.matejkvassay.musiclibrary.Dto.SongDto;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public interface MusicianServiceInterface {
    
    /**
     * Get count of all musicians.
     * @return Count of all musicians.
     */
    public int getAllMusiciansCount();
    
    /**
     * Get count of musicians with name containing given string.
     * @param name Given string.
     * @return Count of musicians with name containing given string.
     */
    public int findMusiciansByNameCount(String name);

    
    /**
     * Get sublist of ordered list of all musicians.
     * @param from Limit from.
     * @param to Limit to.
     * @return Sublist of ordered list of all musicians.
     */
    public List<MusicianDto> getAllMusiciansOrderedByNameLimit(int from, int to);
    
    /**
     * Get sublist of ordered list of all musicians with name containing given string.
     * @param name Given string.
     * @param from Limit from.
     * @param to Limit to.
     * @return Sublist of ordered list of all musicians with name containing given string..
     */
    public List<MusicianDto> findMusiciansByNameOrderedByNameLimit(String name,int from, int to);
    
    /**
     * Get musician of given album.
     * @param albumDto Given album.
     * @return Musician of given album.
     */
    public MusicianDto getMusicianByAlbum(AlbumDto albumDto);
    
    /**
     * Get musician of given song.
     * @param songDto Given song.
     * @return Musician of given song.
     */
    public MusicianDto getMusicianBySong(SongDto songDto);
}
