package sk.matejkvassay.musiclibrary.validation;

import java.util.Calendar;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sk.matejkvassay.musiclibrarybackendapi.dto.AlbumDto;

/**
 *
 * @author Matej Bordáč
 */
@Component
public class AlbumSpringValidation implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return AlbumDto.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AlbumDto album = (AlbumDto) o;
        String title = album.getTitle().replaceAll(" ", "");
        if (title.length() < 1) {
            errors.rejectValue("title", "empty.title");
        }
        
        if (album.getMusician() == null) {
            errors.rejectValue("musician", "empty.musician");
        }
        
        // year between 1000 and 3000
        Calendar cal = Calendar.getInstance();
        if (album.getDateOfRelease() != null) {
            cal.setTime(album.getDateOfRelease());
            int year = cal.get(Calendar.YEAR);
            if (year < 1000 || year > 3000) {
                errors.rejectValue("dateOfRelease", "invalidyear");
            }
        }
    }
    
    
}
