
package sk.matejkvassay.musiclibrary.dao;

import sk.matejkvassay.musiclibrary.entity.User;

/**
 *
 * @author Matej Bordac
 */
public interface UserDao {
    
    User findUserByName(String username);
    
}
