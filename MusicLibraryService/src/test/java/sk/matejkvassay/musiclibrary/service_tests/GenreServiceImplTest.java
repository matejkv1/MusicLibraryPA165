/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.service_tests;

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
import sk.matejkvassay.musiclibrary.DaoContext;
import sk.matejkvassay.musiclibrary.DaoImpl.GenreDaoImpl;
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
    private GenreDaoImpl genreDao;

    @Before
    public void setUp() {
    }

    @Test
    public void addGenreTest(){
        
    }
    
    @Test
    public void removeGenreTest(){
        
    }
    
    @Test
    public void getAllGenresTest(){
        
    }
    
    @Test
    public void findGenreByIdTest(){
        
    }
    
    @Test
    public void findGenreByNameTest(){
        
    }
}
