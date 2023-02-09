package pl.tt.auth.exception;

public class TokenUserMismatchException extends RuntimeException {
    public TokenUserMismatchException(String message) {
        super(message);
    }
}
