package dataStructures;

import java.util.ArrayList;

public class Player {
    private int id;
    private ArrayList<Integer> possiblePlayers;
    private ArrayList<Integer> playedWith;
    public Player(int id) {
        this.id = id;
        playedWith = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getPlayedWith() {
        return playedWith;
    }

    public ArrayList<Integer> getPossiblePlayers() {
        return possiblePlayers;
    }

    public int addToPossiblePlayers(int id) {
        possiblePlayers.add(id);
        return possiblePlayers.size();
    }

    public int addToPlayedWith(int id) {
        playedWith.add(id);
        return playedWith.size();
    }

    public boolean removeFromPossiblePlayers(int id) {
        return possiblePlayers.remove((Integer) id);
    }

    public boolean removeFromPlayedWith(int id) {
        return playedWith.remove((Integer) id);
    }

    public int moveFromPossibleToPlayed(int id) {
        possiblePlayers.remove((Integer) id);
        return addToPlayedWith(id);
    }
}
