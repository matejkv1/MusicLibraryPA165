
package sk.matejkvassay.musiclibrary.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.matejkvassay.musiclibrarybackendapi.dto.UserDto;
import sk.matejkvassay.musiclibrarybackendapi.security.Role;

/**
 *
 * @author
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;
    
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userService.getUserByName(username);
        if (user == null) throw new UsernameNotFoundException(username + " not found");
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
 
        return buildUserForAuthentication(user, authorities);
    }
    
    private User buildUserForAuthentication(UserDto user,
        List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), 
            user.isEnabled(), true, true, true, authorities);
    }
 
    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<>();

        for (Role userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.toString()));
        }

        List<GrantedAuthority> result = new ArrayList<>(setAuths);

        return result;
    }
    
}
