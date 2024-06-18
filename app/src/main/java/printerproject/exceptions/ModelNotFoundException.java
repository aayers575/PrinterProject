package printerproject.exceptions;

/**
 * Exception to throw when a given Material orgId and materialId is not found
 * in the database.
 */
public class ModelNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5061761164717022019L;
    /**
     * Exception to throw when a given Material orgId and materialId is not found
     * in the database.
     */
    public ModelNotFoundException() {
    }
    /**
     * Exception to throw when a given Material orgId and materialId is not found
     * in the database.
     * @param message message
     */
    public ModelNotFoundException(String message) {
        super(message);
    }

}
