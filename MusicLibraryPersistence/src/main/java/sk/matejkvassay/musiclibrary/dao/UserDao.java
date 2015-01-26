
package sk.matejkvassay.musiclibrary.dao;

import java.util.List;
import sk.matejkvassay.musiclibrary.entity.UserEntity;

/**
 *
 * @author Matej Bordac
 */

public interface UserDao {
    void addUser(UserEntity user);
    void updateUser(UserEntity user);
    void deleteUser(UserEntity user);
    UserEntity getUserById(Long id);
    UserEntity getUserByName(String username);
    List<UserEntity> getAllUsers();
}
