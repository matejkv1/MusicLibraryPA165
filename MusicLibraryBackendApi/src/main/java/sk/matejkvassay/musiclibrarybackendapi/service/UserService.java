
package sk.matejkvassay.musiclibrarybackendapi.service;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import sk.matejkvassay.musiclibrarybackendapi.dto.UserDto;

/**
 * Service class for User
 * @author Matej Bordac
 */
public interface UserService {
    
    /**
     * Stores new user
     * @param user user to save
     */
    @PreAuthorize("hasRole('ADMIN')")
    public void addUser(UserDto user);
    
    /**
     * Removes user
     * @param user user to remove
     */
    
    @PreAuthorize("hasRole('ADMIN')")
    public void removeUser(UserDto user);
    
    /**
     * Updates existing user
     * @param user data to update
     */
    @PreAuthorize("hasRole('ADMIN')")
    public void updateUser(UserDto user);
    
    /**
     * Finds user with id
     * @param id id to search for
     * @return found user
     */
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto getUserById(long id);
    
    /**
     * Finds user with name
     * @param name name to look for
     * @return found user
     */
    public UserDto getUserByName(String name);
    
    /**
     * Listing of all users
     * @return list of all users
     */
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> getAllUsers();
}
