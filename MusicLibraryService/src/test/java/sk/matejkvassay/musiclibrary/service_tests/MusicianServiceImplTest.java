/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.service_tests;

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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sk.matejkvassay.musiclibrary.Dao.MusicianDao;
import sk.matejkvassay.musiclibrary.DaoContext;
import sk.matejkvassay.musiclibrary.DaoImpl.Exception.MusicianNameNullException;
import sk.matejkvassay.musiclibrarybackendapi.Dto.AlbumDto;
import sk.matejkvassay.musiclibrarybackendapi.Dto.MusicianDto;
import sk.matejkvassay.musiclibrarybackendapi.Dto.SongDto;
import sk.matejkvassay.musiclibrary.Entity.Album;
import sk.matejkvassay.musiclibrary.Entity.Musician;
import sk.matejkvassay.musiclibrary.Entity.Song;
import sk.matejkvassay.musiclibrarybackendapi.Service.MusicianService;

/**
 *
 * @author Horak
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MusicianServiceImplTest {

    @Inject
    @InjectMocks
    private MusicianService mService;

    @Mock
    private MusicianDao mDaoMock;

    private Musician m1;
    private Musician m2;
    private MusicianDto mDto1;
    private MusicianDto mDto2;
    private List<MusicianDto> listOfMusiciansDto;
    private List<Musician> listOfMusicians;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mDto1 = new MusicianDto();
        mDto1.setId(1L);
        m1 = fromDto(mDto1);

        mDto2 = new MusicianDto();
        mDto2.setId(2L);
        m2 = fromDto(mDto2);

        listOfMusiciansDto = new ArrayList<MusicianDto>();
        listOfMusicians = new ArrayList<Musician>();
    }

    @Test
    public void addMusicianTest() throws MusicianNameNullException {
        Mockito.doReturn(1L).when(mDaoMock).addMusician(m1);

        mService.addMusician(mDto1);

        Mockito.verify(mDaoMock).addMusician(m1);
    }

    @Test
    public void removeMusicianTest() {
        Mockito.doNothing().when(mDaoMock).removeMusician(m1);
        Mockito.doReturn(m1).when(mDaoMock).getMusicianById(mDto1.getId());

        mService.removeMusician(mDto1);

        Mockito.verify(mDaoMock).removeMusician(m1);
    }

    @Test
    public void updateMusicianTest() throws MusicianNameNullException {
        Mockito.doNothing().when(mDaoMock).updateMusician(m1);

        mService.updateMusician(mDto1);

        Mockito.verify(mDaoMock).updateMusician(m1);
    }

    @Test
    public void getAllMusiciansTest() {
        listOfMusicians.add(m1);
        listOfMusicians.add(m2);
        listOfMusiciansDto.add(mDto1);
        listOfMusiciansDto.add(mDto2);

        Mockito.doReturn(listOfMusicians).when(mDaoMock).getAllMusicians();

        assertArrayEquals(mService.getAllMusicians().toArray(), listOfMusiciansDto.toArray());

        Mockito.verify(mDaoMock).getAllMusicians();
    }

    @Test
    public void getMusicianByIdTest() {
        Mockito.doReturn(m1).when(mDaoMock).getMusicianById(mDto1.getId());

        assertEquals(mService.getMusicianById(mDto1.getId()), mDto1);

        Mockito.verify(mDaoMock).getMusicianById(mDto1.getId());
    }

    @Test
    public void getMusicianByAlbumTest() {
        AlbumDto albumDto = new AlbumDto();
        albumDto.setId(1L);
        Album album = new Album();
        album.setId(1L);

        Mockito.doReturn(m1).when(mDaoMock).getMusicianByAlbum(album);

        assertEquals(mService.getMusicianByAlbum(albumDto), mDto1);

        Mockito.verify(mDaoMock).getMusicianByAlbum(album);
    }

    @Test
    public void getMusicianBySongTest() {
        SongDto songDto = new SongDto();
        songDto.setId(1L);
        Song song = new Song();
        song.setId(1L);

        Mockito.doReturn(m1).when(mDaoMock).getMusicianBySong(song);

        assertEquals(mService.getMusicianBySong(songDto), mDto1);

        Mockito.verify(mDaoMock).getMusicianBySong(song);
    }

    private Musician fromDto(MusicianDto musicianDto) {
        if (musicianDto == null) {
            return null;
        }
        Musician musician = new Musician();
        musician.setId(musicianDto.getId());
        musician.setName(musicianDto.getName());
        musician.setBiography(musicianDto.getBiography());

        return musician;
    }
}
