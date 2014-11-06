/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sk.matejkvassay.musiclibrary.Dto;

import java.util.Objects;

/**
 *
 * @author Majo
 */
public class SongDto {
    
    private Long id;
    
    private String title;

    private String commentary;

    private int positionInAlbum;

    private int bitrate;

    private GenreDto genre;
    
    private AlbumDto album;
    
    private MusicianDto musician;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MusicianDto getMusician() {
        return musician;
    }

    public void setMusician(MusicianDto musician) {
        this.musician = musician;
    }

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

    public int getPositionInAlbum() {
        return positionInAlbum;
    }

    public void setPositionInAlbum(int positionInAlbum) {
        this.positionInAlbum = positionInAlbum;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public GenreDto getGenre() {
        return genre;
    }

    public void setGenre(GenreDto genre) {
        this.genre = genre;
    }

    public AlbumDto getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDto album) {
        this.album = album;
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
        final SongDto other = (SongDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
