
package sk.matejkvassay.musiclibrary.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import sk.matejkvassay.musiclibrary.dao.UserDao;
import sk.matejkvassay.musiclibrary.entity.User;
import sk.matejkvassay.musiclibrarybackendapi.dto.UserDto;
import sk.matejkvassay.musiclibrarybackendapi.service.UserService;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
@Service
public class UserServiceImpl implements UserService{
    
    @Inject
    private PlatformTransactionManager txManager;
    
    @Inject
    private UserDao userDao;
    
    public UserServiceImpl() {
    }
    
    @Override
    public void addUser(UserDto user) {
        TransactionStatus status = null;
        
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            userDao.addUser(fromDto(user));
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status);
            }
            throw ex;
        }
    }

    @Override
    public void removeUser(UserDto user) {
        TransactionStatus status = null;
        
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            userDao.removeUser(fromDto(user));
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status);
            }
            throw ex;
        }
    }

    @Override
    public void updateUser(UserDto user) {
        TransactionStatus status = null;
        
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            userDao.updateUser(fromDto(user));
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status);
            }
            throw ex;
        }
    }

    @Override
    public UserDto getUserById(long id) {
        TransactionStatus status = null;
        User user = null;
        
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            user = userDao.getUserById(id);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status);
            }
            throw ex;
        }
        
        return toDto(user);
    }

    @Override
    public UserDto getUserByName(String name) {
        TransactionStatus status = null;
        User user = null;
        
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            user = userDao.getUserByName(name);
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status);
            }
            throw ex;
        }
        
        return toDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        TransactionStatus status = null;
        List<User> users = null;
        
        try {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            status = txManager.getTransaction(def);
            users = userDao.getAllUsers();
            txManager.commit(status);
        } catch (DataAccessException ex) {
            if (!status.isCompleted()) {
                txManager.rollback(status);
            }
            throw ex;
        }
        
        List<UserDto> usersDto = new ArrayList<>();
        if (users != null) {
            for (User user : users) {
                usersDto.add(toDto(user));
            }
        }
        
        return usersDto;
    }
    
    
    public static UserDto toDto(User userEntity){
        if(userEntity==null){
            return null;
        }
        UserDto userDto=new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setEnabled(userEntity.isEnabled());
        userDto.setPassword(userEntity.getPassword());
        userDto.setUsername(userEntity.getUsername());
        userDto.setRole(userEntity.getRole());
        return userDto;
    }
    
    public static User fromDto(UserDto userDto){
        if(userDto==null){
            return null;
        }
        User userEntity=new User();
        userEntity.setId(userDto.getId());
        userEntity.setEnabled(userDto.isEnabled());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setRole(userDto.getRole());
        return userEntity;
    }

}
