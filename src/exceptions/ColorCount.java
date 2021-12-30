package exceptions;

public class ColorCount extends Throwable{
    public ColorCount(String message) {
        System.err.println(message);
    }
}
