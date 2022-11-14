package hexlet.code.exceptions;

public class ParsingFailedException extends Exception {
    public ParsingFailedException(String message, Exception e) {
        super(message + " " + e.getMessage());
    }
}
