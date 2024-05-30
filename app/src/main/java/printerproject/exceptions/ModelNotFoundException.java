package printerproject.exceptions;

/**
 * Exception to throw when a given Material orgId and materialId is not found
 * in the database.
 */
public class ModelNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5061761164717022019L;

    public ModelNotFoundException() {
    }

    public ModelNotFoundException(String message) {
        super(message);
    }

    public ModelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelNotFoundException(Throwable cause) {
        super(cause);
    }

    public ModelNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}