package sk.matejkvassay.musiclibrary.validation;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sk.matejkvassay.musiclibrarybackendapi.dto.GenreDto;
import sk.matejkvassay.musiclibrarybackendapi.service.GenreService;

/**
 *
 * @author
 */
@Component
public class GenreSpringValidation implements Validator {


    @Inject
    private GenreService genreService;
    
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
        
        List<GenreDto> genres = genreService.getAllGenres();
        List<String> names = new ArrayList<>();
        
        for (GenreDto genreDto : genres) {
            names.add(genreDto.getName());
        }
        
        if(genre.getId() == null && names.contains(genre.getName())){
            errors.rejectValue("name", "genre.name.notunique");
        }
        
        
    }
}
