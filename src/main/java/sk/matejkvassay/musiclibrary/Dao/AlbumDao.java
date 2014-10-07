/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.Dao;

import java.util.Set;
import sk.matejkvassay.musiclibrary.Entity.Album;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public interface AlbumDao {
    public void addAlbum(Album album);
    public void removeAlbum(Album album);
    public void updateAlbum(Album album);
    public Set getAllAlbums();
}
