/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.DaoImpl;

import java.util.Set;
import sk.matejkvassay.musiclibrary.Dao.GenreDao;
import sk.matejkvassay.musiclibrary.Entity.Genre;
import javax.persistence.EntityManager;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public class GenreDaoImpl implements GenreDao{
    
    private EntityManager em;
    
    public GenreDaoImpl(EntityManager em){
        this.em=em;
    }
    @Override
    public void addGenre(Genre genre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeGenre(Genre genre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateGenre(Genre genre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set getAllGenre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Genre findGenreById(int id) {
        return em.find(Genre.class, id);
    }
    
}
