/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary;

import java.util.ArrayList;
import java.util.HashSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import sk.matejkvassay.musiclibrary.Entity.Album;
import sk.matejkvassay.musiclibrary.Entity.Genre;
import sk.matejkvassay.musiclibrary.Entity.Song;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */


public class Main {

    public static void main(String[] args){
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
//        EntityManager em = emf.createEntityManager();       
//        em.getTransaction().begin();
//        
//        Album album1=new Album();
//        album1.setCommentary("Commentary commentary commentary.");
//        album1.setTitle("Album title");
//        
//        Genre genre1=new Genre();
//        genre1.setName("Genre name");
//        genre1.setDescription("Genre commentary.");
//        
//        Song song1=new Song();
//        Song song2=new Song();
//        Song song3=new Song();
//        
//        song1.setAlbum(album1);
//        song1.setBitrate(320);
//        song1.setCommentary("Song commentary.");
//        song1.setPositionInAlbum(1);
//        song1.setTitle("Song title.");
//        
//        song2.setAlbum(album1);
//        song2.setBitrate(320);
//        song2.setCommentary("Song commentary.");
//        song2.setPositionInAlbum(2);
//        song2.setTitle("Song title.");
//        
//        song3.setAlbum(album1);
//        song3.setBitrate(320);
//        song3.setCommentary("Song commentary.");
//        song3.setPositionInAlbum(2);
//        song3.setTitle("Song title.");
//        
//        
//        HashSet<Song>songs= new HashSet<Song>();
//        songs.add(song1);
//        songs.add(song2);
//        songs.add(song3);
//        album1.setSongs(songs);
//        
//        
//        em.persist(genre1);
//        em.persist(song1);
//        em.persist(song2);
//        em.persist(song3);
//        em.persist(album1);
//        em.getTransaction().commit();
//        em.close();
//        emf.close();
    }
}
