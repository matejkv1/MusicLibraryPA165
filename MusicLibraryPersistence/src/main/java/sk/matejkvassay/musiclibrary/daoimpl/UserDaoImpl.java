
package sk.matejkvassay.musiclibrary.daoimpl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sk.matejkvassay.musiclibrary.dao.UserDao;
import sk.matejkvassay.musiclibrary.entity.User;

/**
 *
 * @author Matej Bordac
 */
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;
    
    public UserDaoImpl() {
    }
    
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public User findUserByName(String username) {
        List<User> users = new ArrayList<User>();
 
        TypedQuery q = em.createQuery("SELECT u FROM User u WHERE LOWER(u.username) LIKE LOWER(:username)", User.class);
        q.setParameter("username", username);
        users = q.getResultList();
        
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
