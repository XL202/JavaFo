package trfInterpreter;

import exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class reader {

    public static HashMap<Integer, Pentaplet<Integer, Integer, Pair<Double,Integer>, List<Integer>, List<Integer>>>
        //id: {color diff, last colors, {points, ratings}, possible players, matched players}
        checkRules(HashMap<Integer, Pentaplet<String, Integer, Double, Integer, List<Triplet<Integer, String, String>>>> hm) throws PairException, ColorCount, SamePairException {
        //id, rating, points, played with teammates = List: color difference, last colors.
        Iterator<Integer> it = hm.keySet().stream().iterator();
        int i;
        int counter;
        List<Integer> allPlayers = new ArrayList<>(hm.keySet());
        System.out.println(allPlayers);
        List<HashMap<Integer, Triplet<Integer, String, String>>> rounds = new ArrayList<>();
        //list of rounds
        while (it.hasNext()) {
            i = it.next();
            counter = 0;
            for (Triplet<Integer, String, String> triplet : hm.get(i).getFifth()) {
                if (rounds.size() <= counter) rounds.add(new HashMap<>());
                rounds.get(counter).put(i, triplet);
                counter++;
            }


        }
        int c = 1;
        for(HashMap<Integer, Triplet<Integer, String, String>> round : rounds) {
            System.out.println(round);
            //checkRound(round);
            //hash set player with teammate or 0
            Iterator<Integer> iterator = round.keySet().stream().iterator();
            int tmp;
            /*while (iterator.hasNext()) {
                tmp = iterator.next();
                System.out.println(tmp + ": " + round.get(tmp).toString());
            }*/
            iterator = round.keySet().stream().iterator();
            while (iterator.hasNext()) {

                tmp = iterator.next();
                if (round.get(tmp) == null) throw new PairException("No matched teammate in round #" + c + " at player " + tmp);
                if (round.get(tmp).getFirst() != 0) {
                    if (round.get(round.get(tmp).getFirst()) == null) throw new PairException("No teammate found in round #" + c + " at player " + tmp);
                    if (round.get(round.get(tmp).getFirst()).getFirst() != tmp) throw new PairException("No matched teammate in round #" + c + " at player " + tmp);
                    else if (round.get(tmp).getSecond().equals("w") && !round.get(round.get(tmp).getFirst()).getSecond().equals("b")) throw new PairException("No legal game in round #" + c + " at player " + tmp + " as \"w\" with opponent \"w\"!");
                    if (round.get(tmp).getThird().equals("1") && !round.get(round.get(tmp).getFirst()).getThird().equals("0")) throw new PairException("No legal result in round #" + c + " at player " + tmp + " with result \"1\" expected opponent's result \"0\"!");
                    if (round.get(tmp).getThird().equals("0") && !round.get(round.get(tmp).getFirst()).getThird().equals("1")) throw new PairException("No legal result in round #" + c + " at player " + tmp + " with result \"0\" expected opponent's result \"1\"!");
                    if (round.get(tmp).getThird().equals("=") && !round.get(round.get(tmp).getFirst()).getThird().equals("=")) throw new PairException("No legal result in round #" + c + " at player " + tmp + " with result \"=\" expected opponent's result \"=\"!");

                    if (round.get(tmp).getThird().equals("+") && !round.get(round.get(tmp).getFirst()).getThird().equals("-")) throw new PairException("No legal result in round #" + c + " at player " + tmp + " with result \"+\" expected opponent's result \"-\"!");


                    System.out.printf(" %s", round.get(round.get(tmp).getFirst()));
                    System.out.println(" " + round.get(round.get(tmp).getFirst()).getFirst());
                }
                    //get player, which is paired
            }
            System.out.println();
            c++;
        }

        it = hm.keySet().stream().iterator();
        //list of rounds
        List<Integer> currentFrom;
        List<Integer> currentTo;
        HashMap<Integer, Pentaplet<Integer, Integer, Pair<Double,Integer>, List<Integer>, List<Integer>>> out = new HashMap<>();
            //id: {color diff, last colors, {points, ratings}, possible players, matched players}
        int colDiff;
        int lastColors;
        while (it.hasNext()) {
            currentFrom = new ArrayList<>(allPlayers);
            currentTo = new ArrayList<>();
            colDiff = 0;
            lastColors = 0;
            i = it.next();
            counter = 0;

            currentFrom.remove((Integer) i);
            for (Triplet<Integer, String, String> triplet : hm.get(i).getFifth()) {
                counter++;
                if (triplet.getFirst() != 0) {
                    if (!currentFrom.contains(triplet.getFirst())) throw new SamePairException("Cannot be specific pair more than once in one tournament at player id: " + i + " at round: #" + counter);
                    currentTo.add(currentFrom.remove(currentFrom.indexOf(triplet.getFirst())));
                    if (triplet.getSecond().equals("w")) colDiff++;
                    if (triplet.getSecond().equals("b")) colDiff--;
                    if (Math.abs(colDiff) > 2) throw new ColorCount("Color count difference is not from interval <-2;2>. Current: " + colDiff + " at player id: " + i + " at round: #" + counter);
                    if (triplet.getSecond().equals("w") && lastColors >= 0) lastColors++;
                    else if (triplet.getSecond().equals("w") && lastColors < 0) lastColors = 1;
                    else if (triplet.getSecond().equals("b") && lastColors <= 0) lastColors--;
                    else if (triplet.getSecond().equals("b") && lastColors > 0) lastColors = -1;
                    else if (Math.abs(lastColors) > 2) throw new ColorCount("Only two same colors can be in a row. Current: " + lastColors + " at player id: " + i + "at round: #" + counter);
                }
                else currentTo.add(0);
            }
            //id: {color diff, last colors, {points, ratings}, possible players, matched players}
            out.put(i, new Pentaplet<>(colDiff, lastColors, new Pair<>(hm.get(i).getThird(), hm.get(i).getSecond()),currentFrom, currentTo));
        }



        return out;
    }

    /*public static void checkRound(ArrayList<Pair<Integer, Triplet<Integer, String, String>>> round) {

    }*/

    public static HashMap<Integer, Pentaplet<String, Integer, Double, Integer, List<Triplet<Integer, String, String>>>> read(String name) throws FileNotFoundException, ColumnSpacePositions {
        int id = 0;
        try {
            File file = new File(name);
            Scanner sc = new Scanner(file);
            StringBuilder sb;
            HashMap<Integer, Pentaplet<String, Integer, Double, Integer, List<Triplet<Integer, String, String>>>> hm;
            if (!sc.hasNextLine()) {
                return null;
            }
            hm = new HashMap<>();
            List<Triplet<Integer, String, String>> round;
            while (sc.hasNextLine()) {

                sb = new StringBuilder(sc.nextLine());
                if (sb.substring(0,3).equals("001")) {
                    id = Integer.parseInt(sb.substring(4,8).trim());
                    if (id < 1 || id > 9999) throw new NumberRangeException("Id can be only from interval <1,9999>");
                    if (!sb.substring(3,4).equals(" ")) throw new ColumnSpacePositions("Space expected at column " + 3 + "at player with id: " + id);
                    if (!sb.substring(8,9).equals(" ")) throw new ColumnSpacePositions("Space expected at column " + 8 + "at player with id: " + id);
                    if (!sb.substring(13,14).equals(" ")) throw new ColumnSpacePositions("Space expected at column " + 13 + "at player with id: " + id);
                    if (!sb.substring(47,48).equals(" ")) throw new ColumnSpacePositions("Space expected at column " + 47 + "at player with id: " + id);
                    if (!sb.substring(79,80).equals(" ")) throw new ColumnSpacePositions("Space expected at column " + 79 + "at player with id: " + id);
                    if (!sb.substring(84,85).equals(" ")) throw new ColumnSpacePositions("Space expected at column " + 84 + "at player with id: " + id);

                    System.out.println(sb);
                    round = new ArrayList<>();
                    int i = 91;
                    while (sb.length() > i) {
                        if (!sb.substring(i-2,i).equals("  ")) throw new ColumnSpacePositions("Space expected at column " + (i - 2) + ", " + (i - 1) + ".at player with id: " + id);
                        if (!sb.substring(i+4,i+5).equals(" ")) throw new ColumnSpacePositions("Space expected at column " + (i + 4) + "at player with id: " + id);
                        if (!sb.substring(i+6,i+7).equals(" ")) throw new ColumnSpacePositions("Space expected at column " + (i + 7) + "at player with id: " + id);
                        String c;
                        int integer = Integer.parseInt(sb.substring(i,i+4).trim());
                        if (integer < 0 || integer > 9999) throw new NumberRangeException("Id for pairing can be only from interval <1,9999> or 0 at player with id: " + id);
                        c = sb.substring(i+5,i+6);
                        if (integer != 0 && !(c.toLowerCase(Locale.ROOT).equals("w") || c.toLowerCase(Locale.ROOT).equals("b"))) throw new ColorInputError("Color can be only w, b at player with id: " + id);
                        if (integer == 0 && !((c.equals("-")) || c.equals(" "))) throw new ColorInputError("Color for player 0 (none) can by only (-) at player with id: " + id);

                        c = sb.substring(i+7,i+8);
                        if (!(c.equals("+") || c.equals("-") ||
                                c.toUpperCase(Locale.ROOT).equals("W") || c.toUpperCase(Locale.ROOT).equals("D") || c.toUpperCase(Locale.ROOT).equals("L") ||
                                c.equals("1") || c.equals("=") || c.equals("0") ||
                                c.toUpperCase(Locale.ROOT).equals("H") || c.toUpperCase(Locale.ROOT).equals("F") || c.toUpperCase(Locale.ROOT).equals("U") ||
                                c.toUpperCase(Locale.ROOT).equals("Z") || c.toUpperCase(Locale.ROOT).equals(" "))) throw new ResultInputException("Result can be only {+ - W D L 1 = 0 H F U Z} error at player id: " + id);

                        Triplet<Integer, String, String> tmp = new Triplet<>(Integer.valueOf(sb.substring(i,i+4).trim()),sb.substring(i+5,i+6), sb.substring(i+7,i+8));
                        round.add(tmp);
                        i += 10;
                    }
                    double x = Double.parseDouble(sb.substring(80,84));
                    if (x < 0 || x >=100) throw new NumberRangeException("Points can by only in range <0.0,99.9>, error at player " + id);
                    hm.put(Integer.valueOf(sb.substring(4,8).trim()),new Pentaplet<>(sb.substring(14,47), Integer.valueOf(sb.substring(48,52).trim()),
                            Double.valueOf(sb.substring(80,84)), Integer.valueOf(sb.substring(85,89).trim()), round));
                }
            }
            Iterator<Integer> it = hm.keySet().stream().iterator();
            int i;
            while (it.hasNext()) {
                i = it.next();
                System.out.println(i + ": " + hm.get(i).toString());
            }
            return hm;
        } catch (Throwable e){
            System.err.println("Error round data at player: " + id);
        }

        return null;
    }

    public static void main(String[] args) {
        try {
            HashMap<Integer, Pentaplet<String, Integer, Double, Integer, List<Triplet<Integer, String, String>>>> hm;
            hm = read("vstup3_6p.trf");
            if (hm == null) {
                System.err.println("Error in reading file or specified file is empty.");
                return;
            }
            System.out.println(checkRules(hm));
        }
        catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
        }
        catch (NumberFormatException e) {
            System.err.println("NumberFormatException: " + e.getMessage());
        }
        catch (StringIndexOutOfBoundsException e) {
            System.err.println("Nesprávny formát .trf súboru: " + e.getMessage());
        }
        catch (ColumnSpacePositions | PairException | ColorCount | SamePairException message) {
            message.printStackTrace();
        }
    }
}
