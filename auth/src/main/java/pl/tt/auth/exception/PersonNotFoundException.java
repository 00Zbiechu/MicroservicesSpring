package pl.tt.auth.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String username) {
        super(String.format("User with username %s not found", username));
    }
}
