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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import sk.matejkvassay.musiclibrary.Dao.SongDao;
import sk.matejkvassay.musiclibrary.Dto.AlbumDto;
import sk.matejkvassay.musiclibrary.Dto.GenreDto;
import sk.matejkvassay.musiclibrary.Dto.MusicianDto;
import sk.matejkvassay.musiclibrary.Dto.SongDto;
import sk.matejkvassay.musiclibrary.Entity.Album;
import sk.matejkvassay.musiclibrary.Entity.Genre;
import sk.matejkvassay.musiclibrary.Entity.Musician;
import sk.matejkvassay.musiclibrary.Entity.Song;
import sk.matejkvassay.musiclibrary.Service.SongService;

/**
 *
 * @author Horak
 */
@Named
public class SongServiceImpl implements SongService {
    @Inject
    private PlatformTransactionManager txManager;
	
    @Inject
    private SongDao songDao;
	
	public PlatformTransactionManager getTxManager() {
        return txManager;
    }

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public SongDao getSongDao() {
        return songDao;
    }

    public void setSongDao(SongDao songDao) {
        this.songDao = songDao;
    }
    
    private SongDto toDto(Song song) {
        if(song == null){
            return null;
        }
		
		SongDto songDto = new SongDto();
		songDto.setId(song.getId());
		songDto.setBitrate(song.getBitrate());
		songDto.setCommentary(song.getCommentary());
		songDto.setPositionInAlbum(song.getPositionInAlbum());
		songDto.setTitle(song.getTitle());
		
		return songDto;
    }

    private Song fromDto(SongDto songDto) {
        if(songDto == null){
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
	
	@Override
    public void addSong(SongDto songDto) {
		Song song = fromDto(songDto);

        TransactionStatus status = null;
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
			songDao.addSong(song);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status); //if commit failed, it is cleaned, no rollback call needed as said in documantation
            }
            throw ex;
        }
	}
	
	@Override
    public void removeSong(SongDto songDto) {
		TransactionStatus status = null;
        
		try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
			Song songToRemove = songDao.getSongById(songDto.getId());
			songDao.removeSong(songToRemove);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status); //if commit failed, it is cleaned, no rollback call needed as said in documantation
            }
            throw ex;
        }
	}
	
	@Override
    public void updateSong(SongDto song) {
		Song songToUpdate = fromDto(song);

        TransactionStatus status = null;
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
			songDao.updateSong(songToUpdate);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status); //if commit failed, it is cleaned, no rollback call needed as said in documantation
            }
            throw ex;
        }
	}
	
	private List<SongDto> songsListToDto(List<Song> songs) {
		List<SongDto> songsDto = new ArrayList<>();
		
		if(songs != null) {
			for(Song s : songs) {
				songsDto.add(toDto(s));
			}
		}
		
		return songsDto;
	}
	
	@Override
    public List<SongDto> getAllSongs() {
		TransactionStatus status = null;
        List<Song> songs = null;
		
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
			songs = songDao.getAllSongs();
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status); //if commit failed, it is cleaned, no rollback call needed as said in documantation
            }
            throw ex;
        }
		
		List<SongDto> songsDto = songsListToDto(songs);
        
		return songsDto;
	}
	
	@Override
	public List<SongDto> getSongsByName(String nameOfSong) {
		TransactionStatus status = null;
        List<Song> songs = null;
		
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
			songs = songDao.getSongsByName(nameOfSong);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status); //if commit failed, it is cleaned, no rollback call needed as said in documantation
            }
            throw ex;
        }
		
		List<SongDto> songsDto = songsListToDto(songs);
        
		return songsDto;
	}
	
	@Override
	public SongDto getSongById(Long id) {
		TransactionStatus status = null;
		Song song;

		try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
			song = songDao.getSongById(id);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status); //if commit failed, it is cleaned, no rollback call needed as said in documantation
            }
            throw ex;
        }

		SongDto songDto = toDto(song);
        
        return songDto;
	}
	
	@Override
	public List<SongDto> getSongsByAlbum(AlbumDto album) {
		TransactionStatus status = null;
		List<Song> songs;
		
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            Album albumEntity = new Album();
            albumEntity.setId(album.getId());       //only this is needed for DAO layer
            songs = songDao.getSongsByAlbum(albumEntity);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status); //if commit failed, it is cleaned, no rollback call needed as said in documantation
            }
            throw ex;
        }
		
		List<SongDto> songsDto = songsListToDto(songs);
        
        return songsDto;
	}
	
	@Override
	public List<SongDto> getSongsByMusician(MusicianDto musician) {
		TransactionStatus status = null;
		List<Song> songs;
		
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            Musician musicianEntity = new Musician();
			musicianEntity.setId(musician.getId());
            songs = songDao.getSongsByMusician(musicianEntity);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status); //if commit failed, it is cleaned, no rollback call needed as said in documantation
            }
            throw ex;
        }
		
		List<SongDto> songsDto = songsListToDto(songs);
        
        return songsDto;
	}
	
	@Override
	public List<SongDto> getSongsByGenre(GenreDto genre) {
		TransactionStatus status = null;
		List<Song> songs;
		
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
			Genre genreEntity = new Genre();
			genreEntity.setId(genre.getId());
            songs = songDao.getSongsByGenre(genreEntity);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status); //if commit failed, it is cleaned, no rollback call needed as said in documantation
            }
            throw ex;
        }
		
		List<SongDto> songsDto = songsListToDto(songs);
        
        return songsDto;
	}
}
