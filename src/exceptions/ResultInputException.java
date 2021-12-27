package exceptions;

public class ResultInputException extends Throwable{
    public ResultInputException(String message) {
        System.err.println(message);
    }
}
