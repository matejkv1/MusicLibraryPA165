
package sk.matejkvassay.musiclibrary.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import sk.matejkvassay.musiclibrarybackendapi.security.Role;

/**
 *
 * @author Matej Bordac
 */
@Entity
public class User {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;
    
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "role", nullable = false)
    private Role role;
    
    public User(){
    }

    public User(Long id, String username, String password, boolean enabled, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
