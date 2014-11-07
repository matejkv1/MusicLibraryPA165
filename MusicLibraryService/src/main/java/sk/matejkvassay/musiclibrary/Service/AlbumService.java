/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.Service;

import java.util.Date;
import java.util.List;
import sk.matejkvassay.musiclibrary.Dto.AlbumDto;
import sk.matejkvassay.musiclibrary.Dto.MusicianDto;
import sk.matejkvassay.musiclibrary.Dto.SongDto;

/**
 *
 * @author Matej Bordáč
 */
public interface AlbumService {
    
    public void addAlbum(AlbumDto album);
    
    public void removeAlbum(AlbumDto album);
    
    public void updateAlbum(AlbumDto album);
    
    public AlbumDto getAlbumById(Long id);
    
    public List<AlbumDto> getAlbumsByName(String name);
    
    public AlbumDto getAlbumBySong(SongDto song);
    
    public List<AlbumDto> getAlbumsByMusician(MusicianDto musician);
    
    public List<AlbumDto> getAlbumsByDate(Date date);
    
    public List<AlbumDto> getAllAlbums();
    
}
