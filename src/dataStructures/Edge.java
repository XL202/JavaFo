package dataStructures;

public class Edge {
    private int from;
    private int to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getTo() {
        return to;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public void setTo(int to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "E[" + from + "; " + to + ']';
    }

    public boolean isSameEdge(Edge e) {
        return (e.getFrom() == from && e.getTo() == to) || (e.getFrom() == to && e.getTo() == from);
    }
}
