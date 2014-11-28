package sk.matejkvassay.musiclibrary.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sk.matejkvassay.musiclibrarybackendapi.Dto.SongDto;

/**
 *
 * @author
 */
public class SongSpringValidation implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return SongDto.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SongDto song = (SongDto) o;
		if (song.getTitle() == "" || song.getTitle() == null || song.getTitle().trim().length() == 0) {
			errors.rejectValue("title", "song.nameempty");
		}
    }
    
}
