/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibraryserviceapi.apiImpl;

import java.util.List;
import sk.matejkvassay.musiclibrary.Dto.GenreDto;
import sk.matejkvassay.musiclibraryserviceapi.api.GenreServiceApi;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public class GenreServiceApiImpl implements GenreServiceApi{

    @Override
    public int getAllGenresCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GenreDto> getAllGenresOrderedByNameLimit(int from, int to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
