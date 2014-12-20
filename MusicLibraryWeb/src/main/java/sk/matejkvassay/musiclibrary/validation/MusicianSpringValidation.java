package sk.matejkvassay.musiclibrary.validation;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sk.matejkvassay.musiclibrarybackendapi.dto.MusicianDto;
import sk.matejkvassay.musiclibrarybackendapi.service.MusicianService;

/**
 *
 * @author
 */
@Component
public class MusicianSpringValidation implements Validator {

    @Inject
    private MusicianService musicianService;
    
    @Override
    public boolean supports(Class<?> type) {
        return MusicianDto.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MusicianDto musician = (MusicianDto) o;
        String name = musician.getName().replaceAll(" ", "");
        
        //name can't be of zero length
        if(name.isEmpty()){
            errors.rejectValue("name", "musician.name.zerolength");
        }
        
        //if the name is too long
        if(musician.getName().length() > 100){
            errors.rejectValue("name", "musician.name.toolong");
        }
        
        //Obtain all uniquely entered musician names
        List<MusicianDto> musicians = musicianService.getAllMusicians();
        List<String> names = new ArrayList<>();
        
        for (MusicianDto musician1 : musicians) {
            names.add(musician1.getName());
        }
        //if names is already in DB - error, but only if we are creating new musician
        if( musician.getId() == null && names.contains(musician.getName())){
            errors.rejectValue("name", "musician.name.notunique");
        }
        
        if(musician.getBiography().length() > 1000){
            errors.rejectValue("biography", "musician.biography.toolong");
        }
    }

    public MusicianService getMusicianService() {
        return musicianService;
    }

    public void setMusicianService(MusicianService musicianService) {
        this.musicianService = musicianService;
    }
    
    
    
}
