package sk.matejkvassay.musiclibrary.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Matej Bordáč
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MusicianNotFoundException extends RuntimeException {
    public MusicianNotFoundException(String id) {
        super("Could not find musician " + id );
    }
}
