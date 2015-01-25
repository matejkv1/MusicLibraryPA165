
package sk.matejkvassay.musiclibrary.serviceimpl;

import sk.matejkvassay.musiclibrary.entity.User;
import sk.matejkvassay.musiclibrarybackendapi.dto.UserDto;
import sk.matejkvassay.musiclibrarybackendapi.service.UserService;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public class UserServiceImpl implements UserService{
    
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
