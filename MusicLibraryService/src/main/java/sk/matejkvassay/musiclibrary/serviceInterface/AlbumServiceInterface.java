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
public interface AlbumServiceInterface {
    /**
     * Get count of all albums.
     * @return Count of all albums.
     */
    public int getAllAlbumsCount();
    
    /**
     * Get count of albums with names containing value in "name" parameter.
     * @param name String that needs to be contained in albums titles.
     * @return Count of albums.
     */
    public int findAlbumsByNameCount(String name);
    
    /**
     * Get sublist of ordered list of all albums. 
     * @param from Limit from.
     * @param to Limit to.
     * @return Sublist of all albums.
     */
    public List<AlbumDto> getAllAlbumsOrderedByNameLimit(int from, int to);
    
    /**
     * Get sublist of ordered list of all albums with given string in name.
     * @param name String that needs to be contained in name of albums.
     * @param from Limit from.
     * @param to Limit to.
     * @return Sublist of all albums with given string contained in name.
     */
    public List<AlbumDto> findAlbumsByNameOrderedByNameLimit(String name, int from, int to);
    
    /**
     * Get all albums by given musician.
     * @param musicianDto Given musician.
     * @return List of all albums by given musician.
     */
    public List<AlbumDto> getAlbumsByMusicianOrderedByName(MusicianDto musicianDto);
    
    /**
     * Get album that contains specific song.
     * @param song Song that is contained in album.
     * @return Album that contains song.
     */
    public AlbumDto getAlbumBySong(SongDto song);
}
