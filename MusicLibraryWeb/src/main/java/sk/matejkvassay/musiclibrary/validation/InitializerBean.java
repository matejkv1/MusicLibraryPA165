package sk.matejkvassay.musiclibrary.validation;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import sk.matejkvassay.musiclibrary.daoimpl.exception.MusicianNameNullException;
import sk.matejkvassay.musiclibrarybackendapi.dto.AlbumDto;
import sk.matejkvassay.musiclibrarybackendapi.dto.GenreDto;
import sk.matejkvassay.musiclibrarybackendapi.dto.MusicianDto;
import sk.matejkvassay.musiclibrarybackendapi.dto.SongDto;
import sk.matejkvassay.musiclibrarybackendapi.dto.UserDto;
import sk.matejkvassay.musiclibrarybackendapi.security.Role;
import sk.matejkvassay.musiclibrarybackendapi.service.AlbumService;
import sk.matejkvassay.musiclibrarybackendapi.service.GenreService;
import sk.matejkvassay.musiclibrarybackendapi.service.MusicianService;
import sk.matejkvassay.musiclibrarybackendapi.service.SongService;
import sk.matejkvassay.musiclibrarybackendapi.service.UserService;

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
    
    @Inject
    private UserService userService;
    
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
        
        UserDto admin = new UserDto();
        admin.setEnabled(true);
        admin.setUsername("admin");
        admin.setPassword("$2a$10$sNrQj4SpZXi.zDdHApG9zuFrgd6JgU2m9Yq3dpPwOVWyJpnDtXVVq");
        Set<Role> hs = new HashSet<>();
        hs.add(Role.ADMIN);
        admin.setRole(hs);
        userService.addUser(admin);
        
        UserDto user = new UserDto();
        user.setEnabled(true);
        user.setUsername("user");
        user.setPassword("$2a$10$2mtCEYc9/.THqKVGtNTYf./gnuQoOFBm4UwPqGKf8Gwh5p7cK2K9q");
        hs = new HashSet<>();
        hs.add(Role.USER);
        user.setRole(hs);
        userService.addUser(user);
    }
    
}
