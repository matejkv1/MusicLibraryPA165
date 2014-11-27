package sk.matejkvassay.musiclibrary.Dao;

import java.util.List;
import sk.matejkvassay.musiclibrary.Entity.Genre;
import sk.matejkvassay.musiclibrary.Entity.Song;

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
    /**
     * Add new genre.
     * @param genre Genre to be added.
     */
    public void addGenre(Genre genre);
    /**
     * Remove genre.
     * @param genre Genre to be removed.
     */
    public void removeGenre(Genre genre);
    /**
     * Update genre.
     * @param genre Genre to be updated.
     */
    public void updateGenre(Genre genre);
    
    /**
     * Returns list of all genres stored in database.
     * @return java.util.List of all genres. 
     */
    public List getAllGenres();
    /**
     * Get genre by id.
     * @param id Id of genre.
     * @return Genre with given id.
     */
    public Genre findGenreById(long id);
    /**
     * Get genre with given name.
     * @param name Name of genre.
     * @return Genre with given name.
     */
    public Genre findGenreByName(String name);
    
    /**
     * Returns all songs of given genre.
     * @param genre Genre.
     * @return List of songs of given genre.
     */
    public List<Song> getSongsOfGenre(Genre genre);
    
    /**
     * Get genre with given song.
     * @param song Song entity.
     * @return Genre with given song.
     */
    public Genre findGenreBySong(Song song);
}
