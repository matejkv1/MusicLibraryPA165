
package sk.matejkvassay.musiclibrary.validation;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sk.matejkvassay.musiclibrarybackendapi.dto.UserDto;
import sk.matejkvassay.musiclibrarybackendapi.service.UserService;

/**
 *
 * @author
 */
@Component
public class UserSpringValidation implements Validator {
    
    @Inject
    private UserService userService;
    
    @Override
    public boolean supports(Class<?> classInput) {
        return UserDto.class.isAssignableFrom(classInput);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto user = (UserDto) target;
        String name = user.getUsername().replaceAll(" ", "");
        
        if (name.length() < 1  || user.getUsername().length() > 45) {
            errors.rejectValue("username", "user.namesize");
        }
        
        if (user.getPassword().length() < 4  || user.getPassword().length() > 60) {
            errors.rejectValue("password", "user.passsize");
        }
        
        List<UserDto> users = userService.getAllUsers();
        List<String> names = new ArrayList<>();
        
        for (UserDto user1 : users) {
            names.add(user1.getUsername());
        }
        
        if( user.getId() == null && names.contains(user.getUsername())){
            errors.rejectValue("username", "user.name.notunique");
        }
        
        
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    
}
