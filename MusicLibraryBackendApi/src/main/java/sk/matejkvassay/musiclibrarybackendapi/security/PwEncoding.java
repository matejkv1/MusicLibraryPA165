
package sk.matejkvassay.musiclibrarybackendapi.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author
 */
public class PwEncoding {
    
    private PwEncoding(){
    }
    
    public static String getPwHash(String str) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(str);
    }
}
