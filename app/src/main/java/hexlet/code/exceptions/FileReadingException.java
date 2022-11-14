package hexlet.code.exceptions;

public class FileReadingException extends Exception {
    public FileReadingException(String message, Exception e) {
        super(message + " " + e.getMessage());
    }
}
