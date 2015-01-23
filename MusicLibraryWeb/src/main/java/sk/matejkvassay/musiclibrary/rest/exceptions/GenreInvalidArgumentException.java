package sk.matejkvassay.musiclibrary.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Matej Bordáč
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GenreInvalidArgumentException extends RuntimeException {

    public GenreInvalidArgumentException(String id, String message) {
        super("Genre argument error, id: " + id + " message: " + message);
    }
}
