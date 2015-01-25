
package sk.matejkvassay.musiclibrary.daoimpl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import sk.matejkvassay.musiclibrary.dao.UserDao;
import sk.matejkvassay.musiclibrary.entity.User;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;
    
    public UserDaoImpl() {
    }
    
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        em.remove(user);
    }

    @Override
    public User getUserById(Long id) {
        return em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id).getSingleResult();    
    }
    
    @Override
    public User getUserByName(String username) {
        return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username).getSingleResult();  
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}
