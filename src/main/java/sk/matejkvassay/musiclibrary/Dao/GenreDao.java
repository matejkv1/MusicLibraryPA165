package sk.matejkvassay.musiclibrary.Dao;

import java.util.Set;
import sk.matejkvassay.musiclibrary.Entity.Genre;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public interface GenreDao {
    public void addGenre(Genre genre);
    public void removeGenre(Genre genre);
    public void updateGenre(Genre genre);
    public Set getAllGenre();
}
