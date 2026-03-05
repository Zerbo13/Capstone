package Mattiazerbini.backend.Excenptions;

public class DataNoValidationException extends RuntimeException {
    public DataNoValidationException(String message) {
        super(message);
    }
}
