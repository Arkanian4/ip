package arkanian.arkanianexceptions;

/**
 * Exception thrown when a task parameter does not conform to the expected format.
 * For example, if the task description is missing or invalid.
 */
public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException(String message) {
        super(message + "\n");
    }
}
