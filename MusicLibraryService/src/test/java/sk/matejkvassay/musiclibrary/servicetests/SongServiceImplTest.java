package sk.matejkvassay.musiclibrary.servicetests;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sk.matejkvassay.musiclibrary.dao.SongDao;
import sk.matejkvassay.musiclibrary.DaoContext;
import sk.matejkvassay.musiclibrarybackendapi.dto.AlbumDto;
import sk.matejkvassay.musiclibrarybackendapi.dto.GenreDto;
import sk.matejkvassay.musiclibrarybackendapi.dto.MusicianDto;
import sk.matejkvassay.musiclibrarybackendapi.dto.SongDto;
import sk.matejkvassay.musiclibrary.entity.Album;
import sk.matejkvassay.musiclibrary.entity.Genre;
import sk.matejkvassay.musiclibrary.entity.Musician;
import sk.matejkvassay.musiclibrary.entity.Song;
import sk.matejkvassay.musiclibrarybackendapi.service.SongService;

/**
 *
 * @author Horak
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoContext.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SongServiceImplTest {

    @Inject
    @InjectMocks
    private SongService songService;
    @Mock
    private SongDao songDaoMock;
    
    private Song song1;
    private Song song2;
    private SongDto songDto1;
    private SongDto songDto2;
    private List<SongDto> listOfSongsDto;
    private List<Song> listOfSongs;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        songDto1 = new SongDto();
        songDto1.setId(10L);
        song1 = fromDto(songDto1);

        songDto2 = new SongDto();
        songDto2.setId(11L);
        song2 = fromDto(songDto2);

        listOfSongsDto = new ArrayList<SongDto>();
        listOfSongs = new ArrayList<Song>();
    }

    @Test
    public void addSongTest() {
        Mockito.doNothing().when(songDaoMock).addSong(song1);

        songService.addSong(songDto1);

        Mockito.verify(songDaoMock).addSong(song1);
    }

    @Test
    public void removeSongTest() {
        Mockito.doNothing().when(songDaoMock).removeSong(song1);
        Mockito.doReturn(song1).when(songDaoMock).getSongById(songDto1.getId());

        songService.removeSong(songDto1);

        Mockito.verify(songDaoMock).removeSong(song1);
    }

    @Test(expected = TransientDataAccessResourceException.class)
    public void removeSongTest2() {
        Mockito.doThrow(new TransientDataAccessResourceException("")).when(songDaoMock).removeSong(song2);
        Mockito.doReturn(song2).when(songDaoMock).getSongById(songDto2.getId());

        songService.removeSong(songDto2);
    }

    @Test
    public void updateSongTest() {
        Mockito.doNothing().when(songDaoMock).updateSong(song1);

        songService.updateSong(songDto1);

        Mockito.verify(songDaoMock).updateSong(song1);
    }

    @Test
    public void getAllSongsTest() {
        listOfSongs.add(song1);
        listOfSongs.add(song2);
        listOfSongsDto.add(songDto1);
        listOfSongsDto.add(songDto2);

        Mockito.doReturn(listOfSongs).when(songDaoMock).getAllSongs();

        assertArrayEquals(songService.getAllSongs().toArray(), listOfSongsDto.toArray());

        Mockito.verify(songDaoMock).getAllSongs();
    }

    @Test
    public void getSongsByNameTest() {
        listOfSongs.add(song1);
        listOfSongsDto.add(songDto1);

        Mockito.doReturn(listOfSongs).when(songDaoMock).getSongsByName("Olaaa");

        assertArrayEquals(songService.getSongsByName("Olaaa").toArray(), listOfSongsDto.toArray());

        Mockito.verify(songDaoMock).getSongsByName("Olaaa");
    }

    @Test
    public void getSongByIdTest() {
        Mockito.doReturn(song1).when(songDaoMock).getSongById(songDto1.getId());

        assertEquals(songService.getSongById(songDto1.getId()).getId(), songDto1.getId());

        Mockito.verify(songDaoMock).getSongById(songDto1.getId());
    }

    @Test
    public void getSongsByAlbumTest() {
        listOfSongs.add(song2);
        listOfSongsDto.add(songDto2);

        AlbumDto albumDto = new AlbumDto();
        albumDto.setId(1L);
        Album album = new Album();
        album.setId(1L);

        Mockito.doReturn(listOfSongs).when(songDaoMock).getSongsByAlbum(album);

        assertArrayEquals(songService.getSongsByAlbum(albumDto).toArray(), listOfSongsDto.toArray());

        Mockito.verify(songDaoMock).getSongsByAlbum(album);
    }

    @Test
    public void getSongsByMusicianTest() {
        listOfSongs.add(song1);
        listOfSongs.add(song2);
        listOfSongsDto.add(songDto1);
        listOfSongsDto.add(songDto2);

        MusicianDto musicianDto = new MusicianDto();
        musicianDto.setId(1L);
        Musician musician = new Musician();
        musician.setId(1L);

        Mockito.doReturn(listOfSongs).when(songDaoMock).getSongsByMusician(musician);

        assertArrayEquals(songService.getSongsByMusician(musicianDto).toArray(), listOfSongsDto.toArray());

        Mockito.verify(songDaoMock).getSongsByMusician(musician);
    }

    @Test
    public void getSongsByGenreTest() {
        listOfSongs.add(song2);
        listOfSongsDto.add(songDto2);

        GenreDto genreDto = new GenreDto();
        genreDto.setId(1L);
        Genre genre = new Genre();
        genre.setId(1L);

        Mockito.doReturn(listOfSongs).when(songDaoMock).getSongsByGenre(genre);

        assertArrayEquals(songService.getSongsByGenre(genreDto).toArray(), listOfSongsDto.toArray());

        Mockito.verify(songDaoMock).getSongsByGenre(genre);
    }

    private Song fromDto(SongDto songDto) {
        if (songDto == null) {
            return null;
        }

        Song song = new Song();
        song.setId(songDto.getId());
        song.setBitrate(songDto.getBitrate());
        song.setCommentary(songDto.getCommentary());
        song.setPositionInAlbum(songDto.getPositionInAlbum());
        song.setTitle(songDto.getTitle());

        return song;
    }
}
