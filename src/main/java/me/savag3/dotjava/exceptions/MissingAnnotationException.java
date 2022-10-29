package me.savag3.dotjava.exceptions;

/**
 * @author Savag3life
 * @since 1.0
 */
public class MissingAnnotationException extends RuntimeException {

    public MissingAnnotationException(String message) {
        super(message);
    }

    public MissingAnnotationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingAnnotationException(Throwable cause) {
        super(cause);
    }

    public MissingAnnotationException() { }

}
