package exceptions;

public class ColorInputError extends Throwable{
    public ColorInputError(String message) {
        System.err.println(message);
    }
}
