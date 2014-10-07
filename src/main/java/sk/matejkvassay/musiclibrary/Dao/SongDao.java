/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.Dao;

import java.util.Set;
import sk.matejkvassay.musiclibrary.Entity.Song;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public interface SongDao {
    public void addSong(Song song);
    public void removeSong(Song song);
    public void updateSong(Song song);
    public Set getAllSongs();
}
