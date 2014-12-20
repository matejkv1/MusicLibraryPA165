
package sk.matejkvassay.musiclibrarybackendapi.service;

import java.util.List;
import sk.matejkvassay.musiclibrarybackendapi.dto.GenreDto;
import sk.matejkvassay.musiclibrarybackendapi.dto.SongDto;

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
    
    /**
     * Get genre with given song.
     * @param song Song DTO.
     * @return Genre with given song.
     */
    public GenreDto findGenreBySong(SongDto song);
}
