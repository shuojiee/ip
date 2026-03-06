package gin.exception;

/**
 * Represents an application-level exception specific to Gin.
 * Thrown when user input is invalid or an expected operation fails gracefully.
 */
public class GinException extends Exception {

    /**
     * Constructs a GinException with the given message.
     *
     * @param message the error message to display to the user
     */
    public GinException(String message) {
        super(message);
    }
}
