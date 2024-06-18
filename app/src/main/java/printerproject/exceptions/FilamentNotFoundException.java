package printerproject.exceptions;

/**
 * Exception to throw when a given Material orgId and materialId is not found
 * in the database.
 */
public class FilamentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5061761164717022019L;
    /**
     * Exception to throw when a given Material orgId and materialId is not found
     * in the database.
     */
    public FilamentNotFoundException() {
    }
    /**
     * Exception to throw when a given Material orgId and materialId is not found
     * in the database.
     * @param message message
     */
    public FilamentNotFoundException(String message) {
        super(message);
    }

}
