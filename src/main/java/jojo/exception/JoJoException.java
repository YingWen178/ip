package jojo.exception;

/**
 * Represents exceptions specific to the JoJo application.
 * Thrown when an error occurs during command parsing or execution.
 */
public class JoJoException extends Exception {
    /**
     * Creates a new JoJoException with the specified error message.
     *
     * @param message The error message to be displayed to the user.
     */
    public JoJoException(String message) {
        super(message);
    }
}
