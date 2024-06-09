package printerproject.exceptions;

/**
 * Exception to throw when a given Material orgId and materialId is not found
 * in the database.
 */
public class FilamentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5061761164717022019L;

    public FilamentNotFoundException() {
    }

    public FilamentNotFoundException(String message) {
        super(message);
    }

    public FilamentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FilamentNotFoundException(Throwable cause) {
        super(cause);
    }

    public FilamentNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}