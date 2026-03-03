package Mattiazerbini.backend.Excenptions;

import java.util.List;

public class ValidationException extends RuntimeException {
    private List<String> errors;
    public ValidationException(List<String> errors){
        super("Errore di validazione");
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
