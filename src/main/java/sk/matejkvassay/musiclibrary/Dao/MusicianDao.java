/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.Dao;

import java.util.Set;
import sk.matejkvassay.musiclibrary.Entity.Musician;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public interface MusicianDao {
    public void addMusician(Musician musician);
    public void removeMusician(Musician musician);
    public void updateMusician(Musician musician);
    public Set getAllMusicians(Musician musician);
}
