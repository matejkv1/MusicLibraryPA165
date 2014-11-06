/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.service_tests;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import sk.matejkvassay.musiclibrary.Dao.AlbumDao;
import sk.matejkvassay.musiclibrary.DaoContext;
import sk.matejkvassay.musiclibrary.Dto.AlbumDto;
import sk.matejkvassay.musiclibrary.Entity.Album;
import sk.matejkvassay.musiclibrary.ServiceImpl.AlbumServiceImpl;

/**
 *
 * @author Matej Bordáč
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AlbumServiceImplTest {
    
    private AlbumServiceImpl albumService;
    private AlbumDao albumDao;
    @Inject
    private PlatformTransactionManager txManager;

    public AlbumServiceImplTest() {
    }
    
    @Before
    public void setUp() {
        albumDao = mock(AlbumDao.class);
        
        albumService = new AlbumServiceImpl();
        albumService.setAlbumDao(albumDao);
        albumService.setTxManager(txManager);
    }
    
    @Test
    public void addAlbumTest() {
        AlbumDto alb = new AlbumDto();
        alb.setTitle("alb");
        
        Mockito.doNothing().when(albumDao).addAlbum(fromDto(alb));
        
        albumService.addAlbum(alb);
        
        Mockito.verify(albumDao, Mockito.times(1)).addAlbum(fromDto(alb));
        
        // TODO: test exception throwing
    }
    
    @Test
    public void removeAlbumTest() {
        assertTrue(true);
    }
    
    @Test
    public void updateAlbumTest() {
        assertTrue(true);
    }
    
    @Test
    public void getAlbumByIdTest() {
        long fakeId = 123;
        Album alb = new Album();
        alb.setId(fakeId);
        Mockito.doReturn(alb).when(albumDao).getAlbumById(fakeId);
        
        AlbumDto res = albumService.getAlbumById(fakeId);
        
        assertNotNull(res);
        assertEquals("Returned album id didn't match.", (long)res.getId(), fakeId);
        
        // TODO: test exception throwing
    }
    
    @Test
    public void getAlbumsByNameTest() {
        assertTrue(true);
    }
    
    @Test
    public void getAlbumBySongTest() {
        assertTrue(true);
    }
    
    @Test
    public void getAlbumsByMusicianTest() {
        assertTrue(true);
    }
    
    @Test
    public void getAlbumsByDateTest() {
        assertTrue(true);
    }
    
    @Test
    public void getAllAlbumsTest() {
        assertTrue(true);
    }
    
    @Test
    public void getAlbumSongsTest() {
        assertTrue(true);
    }
    
    
    
    
    private AlbumDto toDto(Album album) {
        if(album == null){
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
        if(albumDto == null){
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
