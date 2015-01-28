package sk.matejkvassay.musiclibrary.validation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
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
        
        UserDto admin2 = new UserDto();
        admin2.setEnabled(true);
        admin2.setUsername("admin");
        admin2.setPassword("admin");
        admin2.setRole(Role.ADMIN);
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(admin2.getRole().toString()));
        
        
        
        UserDetails userDetails = new User(admin2.getUsername(), admin2.getPassword(), authorities);

        // Create a token and set the security context

        PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken( userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        
        
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
        
        
        MusicianDto m1 = new MusicianDto();
        m1.setName("Avantasia");
        m1.setBiography("A German progressive metal supergroup project created by Tobias Sammet, vocalist of the band Edguy.");
        try {
            musicianService.addMusician(m1);
        } catch (MusicianNameNullException ex) {
            Logger.getLogger(InitializerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MusicianDto m2 = new MusicianDto();
        m2.setName("ShibayanRecords");
        m2.setBiography("Vocal and instrumental electro and bossa nova. Earlier albums also feature piano arranges by 831, and trance.");
        try {
            musicianService.addMusician(m2);
        } catch (MusicianNameNullException ex) {
            Logger.getLogger(InitializerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MusicianDto m3 = new MusicianDto();
        m3.setName("3L");
        m3.setBiography("3L is a lyricist and vocalist and member of the doujin circle NJK Record.");
        try {
            musicianService.addMusician(m3);
        } catch (MusicianNameNullException ex) {
            Logger.getLogger(InitializerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MusicianDto m4 = new MusicianDto();
        m4.setName("yana");
        m4.setBiography("yana is a Japanese singer/songwriter from Kansai, Japan.");
        try {
            musicianService.addMusician(m4);
        } catch (MusicianNameNullException ex) {
            Logger.getLogger(InitializerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GenreDto g1 = new GenreDto();
        g1.setName("Metal");
        g1.setDescription("Metal is a genre of rock music that developed in the late 1960s and early 1970s, largely in the United Kingdom and the United States.");
        genreService.addGenre(g1);
        
        GenreDto g2 = new GenreDto();
        g2.setName("Electronic");
        g2.setDescription("Electronic music is music that employs electronic musical instruments and electronic music technology in its production, an electronic musician being a musician who composes and/or performs such music.");
        genreService.addGenre(g2);
        
        AlbumDto a1 = new AlbumDto();
        a1.setAlbumArt("http://en.touhouwiki.net/images/9/9b/STAL-1002.jpg");
        a1.setCommentary("ココロバイブレーション is a music album by ShibayanRecords released at Comiket 79.");
        Calendar c1 = Calendar.getInstance();
        c1.set(2010, 12, 30);
        a1.setDateOfRelease(c1.getTime());
        a1.setMusician(musicianService.getMusicianById(3L));
        a1.setTitle("ココロバイブレーション");
        albumService.addAlbum(a1);
        
        AlbumDto a2 = new AlbumDto();
        a2.setAlbumArt("http://upload.wikimedia.org/wikipedia/en/8/8d/The_Metal_Opera.jpg");
        a2.setCommentary("The Metal Opera is the first full-length album by Tobias Sammet's supergroup project, Avantasia.");
        Calendar c2 = Calendar.getInstance();
        c2.set(2001, 1, 11);
        a2.setDateOfRelease(c2.getTime());
        a2.setMusician(musicianService.getMusicianById(2L));
        a2.setTitle("The Metal Opera");
        albumService.addAlbum(a2);
        
        AlbumDto a3 = new AlbumDto();
        a3.setAlbumArt("http://upload.wikimedia.org/wikipedia/en/7/7c/Avantasia_-_The_Scarecrow_-_2008._Front.jpg");
        a3.setCommentary("The Scarecrow is the third full-length album by Tobias Sammet's rock opera project Avantasia.");
        Calendar c3 = Calendar.getInstance();
        c3.set(2008, 1, 25);
        a3.setDateOfRelease(c3.getTime());
        a3.setMusician(musicianService.getMusicianById(2L));
        a3.setTitle("The Scarecrow");
        albumService.addAlbum(a3);
        
        SongDto s1 = new SongDto();
        s1.setAlbum(albumService.getAlbumById(2L));
        s1.setBitrate(320);
        s1.setGenre(genreService.findGenreById(3L));
        s1.setMusician(musicianService.getMusicianById(4L));
        s1.setPositionInAlbum(4);
        s1.setTitle("MyonMyonMyonMyonMyon!");
        songService.addSong(s1);
        
        SongDto s2 = new SongDto();
        s2.setAlbum(albumService.getAlbumById(2L));
        s2.setBitrate(320);
        s2.setGenre(genreService.findGenreById(3L));
        s2.setMusician(musicianService.getMusicianById(5L));
        s2.setPositionInAlbum(5);
        s2.setTitle("花のいろは");
        songService.addSong(s2);
        
        SongDto s3 = new SongDto();
        s3.setAlbum(albumService.getAlbumById(3L));
        s3.setBitrate(320);
        s3.setGenre(genreService.findGenreById(2L));
        s3.setMusician(musicianService.getMusicianById(2L));
        s3.setPositionInAlbum(6);
        s3.setTitle("Farewell");
        songService.addSong(s3);
        
        SongDto s4 = new SongDto();
        s4.setAlbum(albumService.getAlbumById(4L));
        s4.setBitrate(320);
        s4.setGenre(genreService.findGenreById(2L));
        s4.setMusician(musicianService.getMusicianById(2L));
        s4.setPositionInAlbum(11);
        s4.setTitle("Lost In Space");
        songService.addSong(s4);
        
        
        UserDto admin = new UserDto();
        admin.setEnabled(true);
        admin.setUsername("admin");
        admin.setPassword("$2a$10$sNrQj4SpZXi.zDdHApG9zuFrgd6JgU2m9Yq3dpPwOVWyJpnDtXVVq");
        admin.setRole(Role.ADMIN);
        userService.addUser(admin);
        
        UserDto user = new UserDto();
        user.setEnabled(true);
        user.setUsername("user");
        user.setPassword("$2a$10$2mtCEYc9/.THqKVGtNTYf./gnuQoOFBm4UwPqGKf8Gwh5p7cK2K9q");
        user.setRole(Role.USER);
        userService.addUser(user);
        
        UserDto rest = new UserDto();
        rest.setEnabled(true);
        rest.setUsername("rest");
        rest.setPassword("$2a$10$ptEYGJ6SFOEe7hBVC2BAneEcxXE2oyHAxFaa5VX/zDLUJki10PmK6");
        rest.setRole(Role.USER);
        userService.addUser(rest);
        
        SecurityContextHolder.getContext().setAuthentication(null);
    }
    
}
