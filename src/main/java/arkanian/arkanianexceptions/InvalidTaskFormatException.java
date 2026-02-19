package arkanian.arkanianexceptions;

/**
 * Exception thrown when a task string does not conform to the expected format.
 * For example, if the task description is missing or invalid.
 */
public class InvalidTaskFormatException extends RuntimeException {
    public InvalidTaskFormatException(String message) {
        super(message + "\n");
    }
}
