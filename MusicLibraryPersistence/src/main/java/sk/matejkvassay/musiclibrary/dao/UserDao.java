
package sk.matejkvassay.musiclibrary.dao;

import java.util.List;
import sk.matejkvassay.musiclibrary.entity.User;

/**
 *
 * @author Matej Bordac
 */

public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User getUserById(Long id);
    User getUserByName(String username);
    List<User> getAllUsers();
}
