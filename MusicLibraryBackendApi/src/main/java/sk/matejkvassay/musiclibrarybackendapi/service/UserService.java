
package sk.matejkvassay.musiclibrarybackendapi.service;

import java.util.List;
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
    public void addUser(UserDto user);
    
    /**
     * Removes user
     * @param user user to remove
     */
    public void removeUser(UserDto user);
    
    /**
     * Updates existing user
     * @param user data to update
     */
    public void updateUser(UserDto user);
    
    /**
     * Finds user with id
     * @param id id to search for
     * @return found user
     */
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
    public List<UserDto> getAllUsers();
}
