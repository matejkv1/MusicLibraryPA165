/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sk.matejkvassay.musiclibrary.Dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Majo
 */
public class AlbumDto {
    
    private Long id;
    
    private String title;
    
    private String commentary;
    
    private Date dateOfRelease;
    
    private String albumArt;
    
//    private List<SongDto> songs;
    
//    private MusicianDto musician;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public MusicianDto getMusician() {
//        return musician;
//    }

//    public void setMusician(MusicianDto musician) {
//        this.musician = musician;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

//    public List<SongDto> getSongs() {
//        return songs;
//    }

//    public void setSongs(List<SongDto> songs) {
//        this.songs = songs;
//    }

    public Date getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(Date dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(String albumArt) {
        this.albumArt = albumArt;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AlbumDto other = (AlbumDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
