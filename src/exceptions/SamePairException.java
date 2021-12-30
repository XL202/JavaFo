package exceptions;

public class SamePairException extends Throwable {
    public SamePairException(String message) {
            System.err.println(message);
        }
}
