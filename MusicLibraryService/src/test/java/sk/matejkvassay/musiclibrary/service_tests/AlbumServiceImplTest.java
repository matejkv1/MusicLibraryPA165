/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.service_tests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sk.matejkvassay.musiclibrary.Dao.AlbumDao;
import sk.matejkvassay.musiclibrary.DaoContext;
import sk.matejkvassay.musiclibrarybackendapi.Dto.AlbumDto;
import sk.matejkvassay.musiclibrarybackendapi.Dto.MusicianDto;
import sk.matejkvassay.musiclibrarybackendapi.Dto.SongDto;
import sk.matejkvassay.musiclibrary.Entity.Album;
import sk.matejkvassay.musiclibrary.Entity.Musician;
import sk.matejkvassay.musiclibrary.Entity.Song;
import sk.matejkvassay.musiclibrarybackendapi.Service.AlbumService;

/**
 *
 * @author Matej Bordáč
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AlbumServiceImplTest {

    @Inject
    @InjectMocks
    private AlbumService albumService;
    
    @Mock
    private AlbumDao albumDao;

    public AlbumServiceImplTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addAlbumTest() {
        AlbumDto alb = new AlbumDto();
        alb.setTitle("alb");

        Mockito.doNothing().when(albumDao).addAlbum(fromDto(alb));

        albumService.addAlbum(alb);

        Mockito.verify(albumDao, Mockito.times(1)).addAlbum(fromDto(alb));
    }

    @Test
    public void removeAlbumTest() {
        long fakeId = 123;
        AlbumDto alb = new AlbumDto();
        alb.setTitle("alb");
        alb.setId(fakeId);

        Mockito.doReturn(fromDto(alb)).when(albumDao).getAlbumById(fakeId);
        Mockito.doNothing().when(albumDao).removeAlbum(fromDto(alb));

        albumService.removeAlbum(alb);

        Mockito.verify(albumDao, Mockito.times(1)).removeAlbum(fromDto(alb));
    }

    @Test
    public void updateAlbumTest() {
        AlbumDto alb = new AlbumDto();
        alb.setTitle("alb");

        Mockito.doNothing().when(albumDao).updateAlbum(fromDto(alb));

        albumService.updateAlbum(alb);

        Mockito.verify(albumDao, Mockito.times(1)).updateAlbum(fromDto(alb));
    }

    @Test
    public void getAlbumByIdTest() {
        long fakeId = 123;
        Album alb = new Album();
        alb.setId(fakeId);
        Mockito.doReturn(alb).when(albumDao).getAlbumById(fakeId);

        AlbumDto res = albumService.getAlbumById(fakeId);

        assertNotNull("Returned result was null.", res);
        assertEquals("Returned album id didn't match.", (long) res.getId(), fakeId);

    }

    @Test
    public void getAlbumsByNameTest() {
        List<Album> albums = new ArrayList<>();
        List<Album> albums2 = new ArrayList<>();
        Album alb = new Album();
        alb.setTitle("alb");
        albums.add(alb);
        alb = new Album();
        alb.setTitle("album");
        albums.add(alb);
        albums2.add(alb);

        Mockito.doReturn(albums).when(albumDao).getAlbumsByName("alb");
        Mockito.doReturn(albums2).when(albumDao).getAlbumsByName("album");

        List<AlbumDto> res = albumService.getAlbumsByName("alb");
        assertEquals("Returned albums list was different.", res.size(), 2);
        if (res.size() == 2) {
            assertEquals("Returned album 1 title didn't match.", res.get(0).getTitle(), "alb");
            assertEquals("Returned album 2 title didn't match.", res.get(1).getTitle(), "album");
        }

        res = albumService.getAlbumsByName("album");
        assertEquals("Returned albums2 list was different.", res.size(), 1);
        if (res.size() == 1) {
            assertEquals("Returned album didn't match.", res.get(0).getTitle(), "album");
        }
    }

    @Test
    public void getAlbumBySongTest() {
        Song song = new Song();
        song.setTitle("abc");
        List<Song> list = new ArrayList<>();
        list.add(song);

        SongDto songDto = new SongDto();
        songDto.setTitle(song.getTitle());
        songDto.setId(song.getId());

        Album alb = new Album();
        alb.setTitle("alb");
        alb.setSongs(list);

        Mockito.doReturn(alb).when(albumDao).getAlbumBySong(song);

        AlbumDto res = albumService.getAlbumBySong(songDto);
        assertEquals("Returned albums list was different.", res.getId(), alb.getId());
    }

    @Test
    public void getAlbumsByMusicianTest() {
        long fakeId = 123;

        Musician musician = new Musician();
        musician.setName("abc");
        musician.setId(fakeId);

        MusicianDto musicianDto = new MusicianDto();
        musicianDto.setName(musician.getName());
        musicianDto.setId(musician.getId());

        List<Album> albums = new ArrayList<>();

        Album alb = new Album();
        alb.setTitle("alb");
        alb.setMusician(musician);
        albums.add(alb);

        alb = new Album();
        alb.setTitle("album");
        alb.setMusician(musician);
        albums.add(alb);

        Mockito.doReturn(albums).when(albumDao).getAlbumsByMusician(musician);

        List<AlbumDto> res = albumService.getAlbumsByMusician(musicianDto);
        assertEquals("Returned albums list was different.", res.size(), 2);
        if (res.size() == 2) {
            assertEquals("Returned album 1 didn't match.", res.get(0).getTitle(), "alb");
            assertEquals("Returned album 2 didn't match.", res.get(1).getTitle(), "album");
        }
    }

    @Test
    public void getAlbumsByDateTest() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 1, 1);

        List<Album> albums = new ArrayList<>();

        Album alb = new Album();
        alb.setTitle("alb");
        alb.setDateOfRelease(cal.getTime());
        albums.add(alb);

        alb = new Album();
        alb.setTitle("album");
        alb.setDateOfRelease(cal.getTime());
        albums.add(alb);

        Mockito.doReturn(albums).when(albumDao).getAlbumsByDate(cal.getTime());

        List<AlbumDto> res = albumService.getAlbumsByDate(cal.getTime());
        assertEquals("Returned albums list was different.", res.size(), 2);
        if (res.size() == 2) {
            assertEquals("Returned album 1 date didn't match.", res.get(0).getDateOfRelease(), cal.getTime());
            assertEquals("Returned album 2 date didn't match.", res.get(1).getDateOfRelease(), cal.getTime());
        }
    }

    @Test
    public void getAllAlbumsTest() {
        List<Album> albums = new ArrayList<>();
        Album alb = new Album();
        alb.setTitle("alb");
        albums.add(alb);
        alb = new Album();
        alb.setTitle("album");
        albums.add(alb);

        Mockito.doReturn(albums).when(albumDao).getAllAlbums();

        List<AlbumDto> res = albumService.getAllAlbums();
        assertEquals("Returned albums list was different.", res.size(), 2);
        if (res.size() == 2) {
            assertEquals("Returned album 1 didn't match.", res.get(0).getTitle(), "alb");
            assertEquals("Returned album 2 didn't match.", res.get(1).getTitle(), "album");
        }
    }

    private AlbumDto toDto(Album album) {
        if (album == null) {
            return null;
        }
        AlbumDto albumDto = new AlbumDto();
        albumDto.setId(album.getId());
        albumDto.setTitle(album.getTitle());
        //albumDto.setMusician(album.getMusician());
        albumDto.setAlbumArt(album.getAlbumArt());
        albumDto.setDateOfRelease(album.getDateOfRelease());
        albumDto.setCommentary(album.getCommentary());
        //albumDto.setSongs(album.getSongs());

        return albumDto;
    }

    private Album fromDto(AlbumDto albumDto) {
        if (albumDto == null) {
            return null;
        }
        Album album = new Album();
        album.setId(albumDto.getId());
        album.setTitle(albumDto.getTitle());
        //album.setMusician(albumDto.getMusician());
        album.setAlbumArt(albumDto.getAlbumArt());
        album.setDateOfRelease(albumDto.getDateOfRelease());
        album.setCommentary(albumDto.getCommentary());
        //album.setSongs(albumDto.getSongs());

        return album;
    }

}
