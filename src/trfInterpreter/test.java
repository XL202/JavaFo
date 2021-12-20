package trfInterpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class test {
    public static void reader() throws FileNotFoundException {
        File file = new File("vstup3_6p.trf");
        Scanner sc = new Scanner(file);
        StringBuilder sb;
        HashMap<Integer, Pentaplet<String, Integer, Double, Integer, LinkedList<Triplet<Integer, String, String>>>> hm = new HashMap<>();
        LinkedList<Triplet<Integer, String, String>> round;
        while (sc.hasNextLine()) {
            sb = new StringBuilder(sc.nextLine());
            if (sb.substring(0,3).equals("001")) {
                System.out.println(sb);
                round = new LinkedList<>();
                int i = 91;
                while (sb.length() > i +1) {
                    //System.out.printf("%s %s %s | ",sb.substring(i,i+4).trim(), sb.substring(i+5,i+6).trim(), sb.substring(i+7,i+8).trim());
                    Triplet<Integer, String, String> tmp = new Triplet<>(Integer.valueOf(sb.substring(i,i+4).trim()),sb.substring(i+5,i+6), sb.substring(i+7,i+8));
                    round.add(tmp);
                    i += 10;
                }
                //System.out.println();
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

    }
    public static void main(String[] args) throws FileNotFoundException {
        try {
            reader();
        }
        catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
        }
    }
}
