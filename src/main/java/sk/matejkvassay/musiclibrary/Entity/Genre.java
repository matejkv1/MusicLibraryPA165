/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.Entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import java.util.List;
/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */

@Entity
public class Genre {
    
    @Id
    @GeneratedValue
    private Long id;                
    
    @Column(nullable=false, unique=true)
    private String name;            
  
    @Column
    private String description;    
    
    @OneToMany(mappedBy="genre")  
    private List<Song> songs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Genre other = (Genre) obj;
        return true;
    }
    
    @Override
    public String toString(){
        String s="Genre: name="+this.name+", description="+this.description;
        return s;
    }
    
}
