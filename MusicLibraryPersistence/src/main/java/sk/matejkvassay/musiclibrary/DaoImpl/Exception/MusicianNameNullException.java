package sk.matejkvassay.musiclibrary.DaoImpl.Exception;

/**
 * Exception which represents situation when musician's name is null
 * @author Marián Macik
 */
public class MusicianNameNullException extends Exception{

    public MusicianNameNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public MusicianNameNullException(String message) {
        super(message);
    }
}
