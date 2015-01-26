package sk.matejkvassay.musiclibrary.persistencetests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import javax.inject.Inject;
import static org.junit.Assert.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import sk.matejkvassay.musiclibrary.DaoContext;
import sk.matejkvassay.musiclibrary.dao.UserDao;
import sk.matejkvassay.musiclibrary.entity.UserEntity;
import sk.matejkvassay.musiclibrarybackendapi.security.Role;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDaoImplTest {
    @PersistenceContext
    private EntityManager em;
    @Inject
    UserDao udi;
    @Inject
    private PlatformTransactionManager txManager;    
    
    @Before
    public void setUp() {
        
        UserEntity user1=new UserEntity();
        UserEntity user2=new UserEntity();
        UserEntity user3=new UserEntity();
        
        user1.setEnabled(true);
        user1.setPassword("fsafs3421fs");
        user1.setUsername("matejkv1");
        user1.setRole(Role.ADMIN);
        
        user2.setEnabled(true);
        user2.setPassword("safsa34s");
        user2.setUsername("andrej134");
        user2.setRole(Role.USER);
        
        user3.setEnabled(true);
        user3.setPassword("agdagdga4");
        user3.setUsername("veronika2");
        user3.setRole(Role.USER);
        
        TransactionStatus status=beginTransaction();
        
        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        
        txManager.commit(status);
    }
    
    @After
    public void tearDown() {
        TransactionStatus status=beginTransaction();
        em.createQuery("DELETE FROM UserEntity").executeUpdate();
        txManager.commit(status);
        em.close();
    }

     @Test
     public void getAllUsersTest() {     
        List<UserEntity> users=udi.getAllUsers();

        assertEquals(users.size(),3);
        
        
     }
     
     
     @Test
     public void getUserByNameTest(){
        
        UserEntity foundUser=udi.getUserByName("matejkv1");

        assertEquals(foundUser.getRole(),Role.ADMIN);  
     }
     
     @Test
     public void getUserByIdTest(){
         
        UserEntity newUser=new UserEntity();
        newUser.setUsername("getUserById");
        newUser.setPassword("sfafasf34rfsa");
        newUser.setRole(Role.NONE);
        newUser.setEnabled(true);
        
        TransactionStatus status=beginTransaction();
        em.persist(newUser);
        txManager.commit(status);
        long id=newUser.getId();       
        UserEntity foundUser=udi.getUserById(id);

        assertEquals(foundUser.getId(),newUser.getId());  
     }
     
     @Test
     public void addUserTest(){
        TransactionStatus status=beginTransaction();
        UserEntity newUser=new UserEntity();
        newUser.setUsername("getUserById");
        newUser.setPassword("sfafasf34rfsa");
        newUser.setRole(Role.NONE);
        newUser.setEnabled(true);
        udi.addUser(newUser);
        txManager.commit(status);
         
         assertEquals(em.createQuery("SELECT u FROM  UserEntity u", UserEntity.class).getResultList().size(),4);
     
     }
     
     @Test
     public void removeUserTest(){
        TransactionStatus status=beginTransaction();
        UserEntity foundUser=udi.getUserByName("matejkv1");
        udi.deleteUser(foundUser);
        txManager.commit(status);
         
        assertEquals(em.createQuery("SELECT u FROM  UserEntity u", UserEntity.class).getResultList().size(),2);
     
     }
     
     @Test
     public void updateGenreTest(){
        TransactionStatus status=beginTransaction();
        UserEntity foundUser=udi.getUserByName("matejkv1");
        foundUser.setEnabled(false);
        udi.updateUser(foundUser);
        txManager.commit(status); 
        
        foundUser=udi.getUserByName("matejkv1");
  
         
         assertEquals(foundUser.isEnabled(),false);        
         
     }     
     
     public TransactionStatus beginTransaction(){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        return txManager.getTransaction(def);        
     }
}
