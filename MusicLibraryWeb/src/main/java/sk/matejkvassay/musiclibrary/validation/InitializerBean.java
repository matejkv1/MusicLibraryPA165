/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sk.matejkvassay.musiclibrary.validation;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.springframework.stereotype.Component;
import sk.matejkvassay.musiclibrary.DaoImpl.Exception.MusicianNameNullException;
import sk.matejkvassay.musiclibrarybackendapi.Dto.AlbumDto;
import sk.matejkvassay.musiclibrarybackendapi.Dto.GenreDto;
import sk.matejkvassay.musiclibrarybackendapi.Dto.MusicianDto;
import sk.matejkvassay.musiclibrarybackendapi.Dto.SongDto;
import sk.matejkvassay.musiclibrarybackendapi.Service.AlbumService;
import sk.matejkvassay.musiclibrarybackendapi.Service.GenreService;
import sk.matejkvassay.musiclibrarybackendapi.Service.MusicianService;
import sk.matejkvassay.musiclibrarybackendapi.Service.SongService;

/**
 *
 * @author Majo
 */
@Named
@Singleton
public class InitializerBean {
    
    @Inject
    private MusicianService musicianService;
    
    @Inject
    private GenreService genreService;
    
    @Inject 
    private AlbumService albumService;
    
    @Inject
    private SongService songService;
    
    
    @PostConstruct
    public void init() {
        MusicianDto musician = new MusicianDto();
        musician.setName("Bruno Mars");
        musician.setBiography("Young American singer.");
        try {
            musicianService.addMusician(musician);
        } catch (MusicianNameNullException ex) {
            Logger.getLogger(InitializerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GenreDto genre = new GenreDto();
        genre.setName("Pop");
        genre.setDescription("Pop music is a genre of popular music, deriving from rock and roll.");
        genreService.addGenre(genre);
        
        AlbumDto album = new AlbumDto();
        
        
        
        album.setDateOfRelease(new Date());
        album.setTitle("Unorthodox Jukebox");
        album.setMusician(musicianService.getMusicianById(1L));
        albumService.addAlbum(album);
        
        SongDto song = new SongDto();
        
        song.setMusician(musicianService.getMusicianById(1L));
        song.setAlbum(albumService.getAlbumById(1L));
        song.setTitle("Natalie");
        song.setPositionInAlbum(1);
        song.setBitrate(320);
        song.setGenre(genreService.findGenreById(1L));
        songService.addSong(song);
        
        SongDto song2 = new SongDto();
        
        song2.setMusician(musicianService.getMusicianById(1L));
        song2.setAlbum(albumService.getAlbumById(1L));
        song2.setTitle("Grenade");
        song2.setPositionInAlbum(2);
        song2.setBitrate(320);
        song2.setGenre(genreService.findGenreById(1L));
        songService.addSong(song2);
    }
    
}
