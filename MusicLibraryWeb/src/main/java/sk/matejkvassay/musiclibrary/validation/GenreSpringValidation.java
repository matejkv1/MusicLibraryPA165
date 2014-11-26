package sk.matejkvassay.musiclibrary.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sk.matejkvassay.musiclibrarybackendapi.Dto.GenreDto;

/**
 *
 * @author
 */
public class GenreSpringValidation implements Validator {


    @Override
    public boolean supports(Class<?> classInput) {
        return GenreDto.class.isAssignableFrom(classInput);
    }

    @Override
    public void validate(Object target, Errors errors) {
        GenreDto genre = (GenreDto) target;
        if (genre.getName()=="" || genre.getName()==null) {
            errors.rejectValue("name", "genre.nameempty");
        }
    }
}
