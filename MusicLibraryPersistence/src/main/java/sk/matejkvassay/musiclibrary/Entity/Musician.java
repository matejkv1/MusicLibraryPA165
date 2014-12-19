
package sk.matejkvassay.musiclibrary.Entity;

import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
/**
 * Entity which represents musician in application
 * @author Marián Macik
 */

@Entity
public class Musician {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false, length = 120, unique = true)
    private String name;
    
    @Column(length = 1000)
    private String biography;
    
    @OneToMany(mappedBy="musician", cascade = CascadeType.REMOVE)
    private Set<Album> albums;
    
    @OneToMany(mappedBy="musician", cascade = CascadeType.REMOVE)
    private Set<Song>songs;

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Musician other = (Musician) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
