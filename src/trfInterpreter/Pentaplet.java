package trfInterpreter;

import java.util.Objects;

public class Pentaplet<T1,T2,T3,T4,T5> {
    private final T1 first;
    private final T2 second;
    private final T3 third;
    private final T4 fourth;
    private final T5 fifth;

    public Pentaplet(T1 first, T2 second, T3 third, T4 fourth, T5 fifth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    public T3 getThird() {
        return third;
    }

    public T4 getFourth() {
        return fourth;
    }

    public T5 getFifth() {
        return fifth;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pentaplet<?, ?, ?, ?, ?> pentaplet = (Pentaplet<?, ?, ?, ?, ?>)  o;
        return Objects.equals(first, pentaplet.first) && Objects.equals(second, pentaplet.second) &&
                Objects.equals(third, pentaplet.third) && Objects.equals(fourth, pentaplet.fourth) && Objects.equals(fifth, pentaplet.fifth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third, fourth, fifth);
    }

    @Override
    public String toString() {
        return "P: {" + first.toString() + " " + second.toString() + " " + third.toString() + " " + fourth.toString() + " " + fifth.toString();
    }
}
