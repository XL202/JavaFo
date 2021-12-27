package exceptions;

public class NumberRangeException extends Throwable{
    public NumberRangeException(String message) {
        System.err.println(message);
    }
}
