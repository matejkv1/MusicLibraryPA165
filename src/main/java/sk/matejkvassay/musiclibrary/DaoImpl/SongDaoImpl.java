/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.DaoImpl;

import java.util.Set;
import sk.matejkvassay.musiclibrary.Dao.SongDao;
import sk.matejkvassay.musiclibrary.Entity.Song;
import javax.persistence.EntityManager;
/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public class SongDaoImpl implements SongDao{
    public SongDaoImpl(EntityManager em){
        
    }
    @Override
    public void addSong(Song song) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSong(Song song) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSong(Song song) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set getAllSongs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
