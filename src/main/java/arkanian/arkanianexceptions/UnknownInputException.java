package arkanian.arkanianexceptions;

public class UnknownInputException extends RuntimeException {
    public UnknownInputException(String message) {
        super(message + "\n");
    }
}
