package printerproject.exceptions;

/**
 * Exception to throw when a given Material orgId and materialId is not found
 * in the database.
 */
public class VoteNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5061761164717022019L;

    public VoteNotFoundException() {
    }

    public VoteNotFoundException(String message) {
        super(message);
    }

    public VoteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VoteNotFoundException(Throwable cause) {
        super(cause);
    }

    public VoteNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}