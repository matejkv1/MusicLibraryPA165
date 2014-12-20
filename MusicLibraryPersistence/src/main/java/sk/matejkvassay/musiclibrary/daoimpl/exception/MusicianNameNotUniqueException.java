package sk.matejkvassay.musiclibrary.daoimpl.exception;

/**
 * Exception which represents situation when musician's name is not unique
 * @author Marián Macik
 */
public class MusicianNameNotUniqueException extends Exception {

    public MusicianNameNotUniqueException(String message) {
        super(message);
    }

    public MusicianNameNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
