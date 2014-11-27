/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import sk.matejkvassay.musiclibrary.Dao.GenreDao;
import sk.matejkvassay.musiclibrarybackendapi.Dto.GenreDto;
import sk.matejkvassay.musiclibrary.Entity.Genre;
import sk.matejkvassay.musiclibrarybackendapi.Service.GenreService;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
@Service
public class GenreServiceImpl implements GenreService{

    @Inject
    private PlatformTransactionManager txManager;

    @Inject
    private GenreDao genreDao;
    @Override
    public void addGenre(GenreDto genreDto) {
        Genre genreToSave = dtoToEntity(genreDto);

        TransactionStatus status = null;
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            genreDao.addGenre(genreToSave);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status);
            }
            throw ex;
        }
    }

    @Override
    public void removeGenre(GenreDto genreDto) {
        Genre genreToRemove = dtoToEntity(genreDto);

        TransactionStatus status = null;
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            genreDao.removeGenre(genreToRemove);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status);
            }
            throw ex;
        }
    }

    @Override
    public void updateGenre(GenreDto genreDto) {
        Genre genreToUpdate = dtoToEntity(genreDto);

        TransactionStatus status = null;
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            genreDao.updateGenre(genreToUpdate);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status);
            }
            throw ex;
        }
    }

    @Override
    public List<GenreDto> getAllGenres() {
        ArrayList<Genre> genres=null;
        TransactionStatus status = null;
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            genres=(ArrayList<Genre>)genreDao.getAllGenres();
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status);
            }
            throw ex;
        }
        ArrayList<GenreDto> genresDto=new ArrayList<GenreDto>();
        for(Genre g:genres){
            genresDto.add(entityToDto(g));
        }
        
        return genresDto;
    }

    @Override
    public GenreDto findGenreById(long id) {
        TransactionStatus status = null;
        Genre genre=null;
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            genre=genreDao.findGenreById(id);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status);
            }
            throw ex;
        }
        GenreDto genreDto=entityToDto(genre);
        return genreDto;
    }

    @Override
    public GenreDto findGenreByName(String name) {
        Genre genre=null;
        TransactionStatus status = null;
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            genre=genreDao.findGenreByName(name);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status);
            }
            throw ex;
        }
        
        GenreDto genreDto=entityToDto(genre);
        return genreDto;
    }
    
    private Genre dtoToEntity(GenreDto genreDto){
        Genre genre=new Genre();
        genre.setId(genreDto.getId());
        genre.setDescription(genreDto.getDescription());
        genre.setName(genreDto.getName());
        return genre;
    }
    
    private GenreDto entityToDto(Genre genre){
        GenreDto genreDto = new GenreDto();
        genreDto.setId(genre.getId());
        genreDto.setDescription(genre.getDescription());
        genreDto.setName(genre.getName());
        return genreDto;
    }

    public PlatformTransactionManager getTxManager() {
        return txManager;
    }

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public GenreDao getGenreDao() {
        return genreDao;
    }

    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }  
}
