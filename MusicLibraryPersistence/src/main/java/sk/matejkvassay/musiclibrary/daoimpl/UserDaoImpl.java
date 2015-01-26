
package sk.matejkvassay.musiclibrary.daoimpl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import sk.matejkvassay.musiclibrary.dao.UserDao;
import sk.matejkvassay.musiclibrary.entity.UserEntity;

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
    public void addUser(UserEntity user) {
        em.persist(user);
    }

    @Override
    public void updateUser(UserEntity user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(UserEntity user) {
        em.remove(user);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return em.createQuery("SELECT u FROM UserEntity u WHERE u.id = :id", UserEntity.class)
                .setParameter("id", id).getSingleResult();    
    }
    
    @Override
    public UserEntity getUserByName(String username) {
        return em.createQuery("SELECT u FROM UserEntity u WHERE u.username = :username", UserEntity.class)
                .setParameter("username", username).getSingleResult();  
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return em.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();
    }
}
