package sk.matejkvassay.musiclibrary.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sk.matejkvassay.musiclibrarybackendapi.Dto.AlbumDto;

/**
 *
 * @author Matej Bordáč
 */
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
    }
    
}
