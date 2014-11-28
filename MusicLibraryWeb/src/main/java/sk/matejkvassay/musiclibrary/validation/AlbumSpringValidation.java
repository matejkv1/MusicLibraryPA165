package sk.matejkvassay.musiclibrary.validation;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sk.matejkvassay.musiclibrarybackendapi.Dto.AlbumDto;
import sk.matejkvassay.musiclibrarybackendapi.Service.AlbumService;

/**
 *
 * @author Matej Bordáč
 */
@Component
public class AlbumSpringValidation implements Validator {

//    @Inject
//    private AlbumService albumService;
    
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
        // date format validation
        // msg: wrongFormat.date
    }
    
//    public AlbumService getAlbumService() {
//        return albumService;
//    }
//
//    public void setAlbumService(AlbumService albumService) {
//        this.albumService = albumService;
//    }
    
}
