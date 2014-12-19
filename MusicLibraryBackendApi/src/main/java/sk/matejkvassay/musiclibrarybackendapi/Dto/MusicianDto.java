package sk.matejkvassay.musiclibrarybackendapi.Dto;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO which represents musician in application
 * @author Marián Macik
 */
@XmlRootElement
public class MusicianDto {

    private Long id;

    private String name;

    private String biography;

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
        int hash = 7;
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
        final MusicianDto other = (MusicianDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MusicianDto{" + "id=" + id + ", name=" + name + ", biography=" + biography + '}';
    }
}
