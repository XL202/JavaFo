package exceptions;

public class ColumnSpacePositions extends Throwable{
    public ColumnSpacePositions(String message) {
        System.err.println(message);
    }
}
