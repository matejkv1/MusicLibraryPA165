/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.Service;

import java.util.List;
import sk.matejkvassay.musiclibrary.Dto.GenreDto;
import sk.matejkvassay.musiclibrary.Entity.Genre;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public interface GenreService {
    /**
     * Add new genre.
     * @param genre Genre to be added.
     */
    public void addGenre(GenreDto genre);
    /**
     * Remove genre.
     * @param genre Genre to be removed.
     */
    public void removeGenre(GenreDto genre);
    /**
     * Update genre.
     * @param genre Genre to be updated.
     */
    public void updateGenre(GenreDto genre);
    
    /**
     * Returns list of all genres stored in database.
     * @return java.util.List of all genres. 
     */
    public List<GenreDto> getAllGenres();
    /**
     * Get genre by id.
     * @param id Id of genre.
     * @return Genre with given id.
     */
    public GenreDto findGenreById(long id);
    /**
     * Get genre with given name.
     * @param name Name of genre.
     * @return Genre with given name.
     */
    public GenreDto findGenreByName(String name);

}
