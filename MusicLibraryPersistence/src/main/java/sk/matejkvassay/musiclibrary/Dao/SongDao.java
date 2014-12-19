package sk.matejkvassay.musiclibrary.Dao;

import java.util.List;
import sk.matejkvassay.musiclibrary.Entity.Album;
import sk.matejkvassay.musiclibrary.Entity.Genre;
import sk.matejkvassay.musiclibrary.Entity.Musician;
import sk.matejkvassay.musiclibrary.Entity.Song;

/**
 *
 * @author Horak
 */
public interface SongDao {

    /**
     * Adds an Song into DB.
     *
     * @param song Song to be added.
     */
    public void addSong(Song song);

    /**
     * Removes Song from DB.
     *
     * @param song Song to be removed.
     */
    public void removeSong(Song song);

    /**
     * Updates Song.
     *
     * @param song Song to be updated.
     */
    public void updateSong(Song song);

    /**
     * Retreives all songs from DB.
     *
     * @return List of retreived songs.
     */
    public List<Song> getAllSongs();

    /**
     * Retreives songs with specific title.
     *
     * @param nameOfSong Part of the name of the song.
     * @return List of retreived songs.
     */
    public List<Song> getSongsByName(String nameOfSong);

    /**
     * Retreives song with specific id.
     *
     * @param id Id of the song.
     * @return Song with specific ID.
     */
    public Song getSongById(Long id);

    /**
     * Retreives songs from specific album.
     *
     * @param album Album to which the songs belong.
     * @return List of retreived songs.
     */
    public List<Song> getSongsByAlbum(Album album);

    /**
     * Retreives songs from specific musician.
     *
     * @param musician Musician who created those songs.
     * @return List of retreived songs.
     */
    public List<Song> getSongsByMusician(Musician musician);

    /**
     * Retreives songs with specific genre.
     *
     * @param genre Genre of the songs.
     * @return List of retreived songs.
     */
    public List<Song> getSongsByGenre(Genre genre);
}
