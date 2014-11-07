/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.service_tests;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
import sk.matejkvassay.musiclibrary.Dao.GenreDao;
import sk.matejkvassay.musiclibrary.DaoContext;
import sk.matejkvassay.musiclibrary.DaoImpl.GenreDaoImpl;
import sk.matejkvassay.musiclibrary.Dto.AlbumDto;
import sk.matejkvassay.musiclibrary.Dto.GenreDto;
import sk.matejkvassay.musiclibrary.Entity.Album;
import sk.matejkvassay.musiclibrary.Entity.Genre;
import sk.matejkvassay.musiclibrary.ServiceImpl.AlbumServiceImpl;
import sk.matejkvassay.musiclibrary.ServiceImpl.GenreServiceImpl;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GenreServiceImplTest {
    
    private GenreServiceImpl genreService;
    private GenreDao genreDaoMock;
    
    @Inject
    private PlatformTransactionManager txManager;

    @Before
    public void setUp() {
        genreDaoMock = mock(GenreDao.class);
        genreService = new GenreServiceImpl();
        genreService.setGenreDao(genreDaoMock);
        genreService.setTxManager(txManager);
    }

    @Test
    public void addGenreTest(){
        GenreDto genre = new GenreDto();
        Mockito.doNothing().when(genreDaoMock).addGenre(dtoToEntity(genre));
        genreService.addGenre(genre);
        Mockito.verify(genreDaoMock, Mockito.times(1)).addGenre(dtoToEntity(genre));
    }
    
    @Test
    public void removeGenreTest(){
        GenreDto genre = new GenreDto();
        Mockito.doNothing().when(genreDaoMock).removeGenre(dtoToEntity(genre));
        genreService.removeGenre(genre);
        Mockito.verify(genreDaoMock, Mockito.times(1)).removeGenre(dtoToEntity(genre));        
    }
    
    @Test
    public void updateGenreTest(){
        GenreDto genre = new GenreDto();
        Mockito.doNothing().when(genreDaoMock).updateGenre(dtoToEntity(genre));
        genreService.updateGenre(genre);
        Mockito.verify(genreDaoMock, Mockito.times(1)).updateGenre(dtoToEntity(genre));
    }
    
    @Test
    public void getAllGenresTest(){
        List<Genre> genres = new ArrayList<>();
        
        Genre genre = new Genre();
        genre.setDescription("gen1");
        Genre genre2 = new Genre();
        genre2.setDescription("gen2");   
        Genre genre3 = new Genre();
        genre3.setDescription("gen3");
        
        genres.add(genre);
        genres.add(genre2);
        genres.add(genre3);
        
        Mockito.doReturn(genres).when(genreDaoMock).getAllGenres();
        
        List<GenreDto> resultGenres = genreService.getAllGenres();
        
        assertEquals(resultGenres.size(), 3);
        assertEquals(resultGenres.get(0).getDescription(), "gen1");
        assertEquals(resultGenres.get(1).getDescription(), "gen2");
        assertEquals(resultGenres.get(2).getDescription(), "gen3");
    }
    
    @Test
    public void findGenreByIdTest(){
        Genre genre = new Genre();
        genre.setDescription("gen");

        Mockito.doReturn(genre).when(genreDaoMock).findGenreById(0L);
        
        GenreDto resultGenre = genreService.findGenreById(0L);
        
        assertEquals(resultGenre.getDescription(), "gen");     
    }
    
    @Test
    public void findGenreByNameTest(){
        Genre genre = new Genre();
        genre.setDescription("gen");

        Mockito.doReturn(genre).when(genreDaoMock).findGenreByName("name");
        
        GenreDto resultGenre = genreService.findGenreByName("name");
        
        assertEquals(resultGenre.getDescription(), "gen");           
    }
    
    private Genre dtoToEntity(GenreDto genreDto){
        Genre genre=new Genre();
        genre.setId(genreDto.getId());
        genre.setDescription(genreDto.getDescription());
        genre.setName(genreDto.getName());
        return genre;
    }
}
