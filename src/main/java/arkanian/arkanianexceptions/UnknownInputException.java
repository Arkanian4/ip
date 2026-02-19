package arkanian.arkanianexceptions;

/**
 * Exception thrown when the user inputs a command or instruction
 * that the application does not recognize.
 */
public class UnknownInputException extends RuntimeException {
    public UnknownInputException(String message) {
        super(message + "\n");
    }
}
