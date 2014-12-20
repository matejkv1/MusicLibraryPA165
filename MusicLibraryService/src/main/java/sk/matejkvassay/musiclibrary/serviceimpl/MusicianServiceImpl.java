package sk.matejkvassay.musiclibrary.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import sk.matejkvassay.musiclibrary.dao.MusicianDao;
import sk.matejkvassay.musiclibrary.daoimpl.exception.MusicianNameNullException;
import sk.matejkvassay.musiclibrarybackendapi.dto.AlbumDto;
import sk.matejkvassay.musiclibrarybackendapi.dto.MusicianDto;
import sk.matejkvassay.musiclibrarybackendapi.dto.SongDto;
import sk.matejkvassay.musiclibrary.entity.Album;
import sk.matejkvassay.musiclibrary.entity.Musician;
import sk.matejkvassay.musiclibrary.entity.Song;
import sk.matejkvassay.musiclibrarybackendapi.service.MusicianService;

/**
 *
 * @author Marián Macik
 */
@Service
public class MusicianServiceImpl implements MusicianService {

    @Inject
    private PlatformTransactionManager txManager;

    @Inject
    private MusicianDao musicianDao;

    @Override
    public Long addMusician(MusicianDto musician) throws MusicianNameNullException {

        Musician musicianToSave = fromDto(musician);

        TransactionStatus status = null;
        Long ret;
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            ret = musicianDao.addMusician(musicianToSave);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status); //if commit failed, it is cleaned, no rollback call needed as said in documantation
            }
            throw ex;
        }
        return ret;
    }

    @Override
    public void removeMusician(MusicianDto musician) {
        TransactionStatus status = null;
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            //need to obtain managed entity to delete
            Musician musicianToRemove = musicianDao.getMusicianById(musician.getId());
            musicianDao.removeMusician(musicianToRemove);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status); //if commit failed, it is cleaned, no rollback call needed as said in documantation
            }
            throw ex;
        }
    }

    @Override
    public void updateMusician(MusicianDto musician) throws MusicianNameNullException{
        Musician musicianToUpdate = fromDto(musician);

        TransactionStatus status = null;
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            musicianDao.updateMusician(musicianToUpdate);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status); //if commit failed, it is cleaned, no rollback call needed as said in documantation
            }
            throw ex;
        }
    }

    @Override
    public List<MusicianDto> getAllMusicians() {
        TransactionStatus status = null;
        List<Musician> musicians = null;
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            musicians = musicianDao.getAllMusicians();
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status); //if commit failed, it is cleaned, no rollback call needed as said in documantation
            }
            throw ex;
        }
        List<MusicianDto> musiciansDto = new ArrayList<>();
        if(musicians != null){
            for(Musician musician : musicians){
                MusicianDto musicianDto = toDto(musician);
                musiciansDto.add(musicianDto);
            }
        }
        return musiciansDto;
    }

    @Override
    public MusicianDto getMusicianById(Long id) {
        TransactionStatus status = null;
        Musician musician = null;
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            musician = musicianDao.getMusicianById(id);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status); //if commit failed, it is cleaned, no rollback call needed as said in documantation
            }
            throw ex;
        }
        MusicianDto musicianDto = toDto(musician);
        
        return musicianDto;
    }

    @Override
    public MusicianDto getMusicianByAlbum(AlbumDto album) {
        TransactionStatus status = null;
        Musician musician = null;
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            Album albumEntity = new Album();
            albumEntity.setId(album.getId());       //only this is needed for DAO layer
            musician = musicianDao.getMusicianByAlbum(albumEntity);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status); //if commit failed, it is cleaned, no rollback call needed as said in documantation
            }
            throw ex;
        }
        MusicianDto musicianDto = toDto(musician);
        
        return musicianDto;
    }

    @Override
    public MusicianDto getMusicianBySong(SongDto song) {
        TransactionStatus status = null;
        Musician musician = null;
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            Song songEntity = new Song();
            songEntity.setId(song.getId());       //only this is needed for DAO layer
            musician = musicianDao.getMusicianBySong(songEntity);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status); //if commit failed, it is cleaned, no rollback call needed as said in documantation
            }
            throw ex;
        }
        MusicianDto musicianDto = toDto(musician);
        
        return musicianDto;
    }
    

    public PlatformTransactionManager getTxManager() {
        return txManager;
    }

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public MusicianDao getMusicianDao() {
        return musicianDao;
    }

    public void setMusicianDao(MusicianDao musicianDao) {
        this.musicianDao = musicianDao;
    }
    
    public static MusicianDto toDto(Musician musician) {
        if(musician == null){
            return null;
        }
        MusicianDto musicianDto = new MusicianDto();
        musicianDto.setId(musician.getId());
        musicianDto.setName(musician.getName());
        musicianDto.setBiography(musician.getBiography());

        return musicianDto;
    }

    public static Musician fromDto(MusicianDto musicianDto) {
        if(musicianDto == null){
            return null;
        }
        Musician musician = new Musician();
        musician.setId(musicianDto.getId());
        musician.setName(musicianDto.getName());
        musician.setBiography(musicianDto.getBiography());

        return musician;
    }
}
