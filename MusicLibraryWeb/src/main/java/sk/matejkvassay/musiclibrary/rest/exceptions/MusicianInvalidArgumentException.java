
package sk.matejkvassay.musiclibrary.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Matej Bordáč
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MusicianInvalidArgumentException extends RuntimeException {

    public MusicianInvalidArgumentException(String id, String message) {
        super("Musician argument error, id: " + id + " message: " + message);
    }
}