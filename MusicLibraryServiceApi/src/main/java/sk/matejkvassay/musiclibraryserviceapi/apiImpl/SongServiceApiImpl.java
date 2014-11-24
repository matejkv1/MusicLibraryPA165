/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibraryserviceapi.apiImpl;

import java.util.List;
import sk.matejkvassay.musiclibrary.Dto.AlbumDto;
import sk.matejkvassay.musiclibrary.Dto.GenreDto;
import sk.matejkvassay.musiclibrary.Dto.SongDto;
import sk.matejkvassay.musiclibrary.serviceInterface.SongServiceApi;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public class SongServiceApiImpl implements SongServiceApi{

    @Override
    public int getAllSongsCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int findSongsByNameCount(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSongsByGenreCount(GenreDto genre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SongDto> getAllSongsOrderedByNameLimit(int from, int to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SongDto> getSongsByAlbumOrderedByAlbumOrder(AlbumDto albumDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SongDto> findSongsByNameOrderedByNameLimit(String name, int from, int to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SongDto> getSongsByGenreOrderedByNameLimit(GenreDto genreDto, int from, int to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
