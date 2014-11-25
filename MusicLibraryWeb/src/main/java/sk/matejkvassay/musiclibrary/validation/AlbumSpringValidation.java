package sk.matejkvassay.musiclibrary.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Matej Bordáč
 */
public class AlbumSpringValidation implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void validate(Object o, Errors errors) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
