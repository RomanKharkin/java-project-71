package hexlet.code.exceptions;

public class FormatFailedException extends Exception {
    public FormatFailedException(String reason) {

    }
    public FormatFailedException(String message, Exception e) {
        super(message + " " + e.getMessage());
    }
}
