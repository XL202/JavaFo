package BB_Pairing;


import dataStructures.Edge;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class getCardinality {
    //poznamka: nemoze byt "diera", playeri musia byt v dvojiciach obsiahnuty bez vynechania
    // (t.j. nemoze byt v paroch 0,1,2,4,5  a bez 3)

    public static int bbCardinality(String BB_Path, ArrayList<Edge> edges, ArrayList<Boolean> notPlay, int max) {
        // notPlay ==> list hracov, ktory nie su obsiahnuty v edges (musim) mat suvisly rad,
        // preto posuvam tak, aby bol suvisly rad
        HashMap<Integer, Integer> hm = new HashMap<>();
        int counter = 0;
        for(int i=0; i<max; i++) {
            if (notPlay.get(i)) counter++;
            else hm.put(i, i-counter);
        }
        System.out.println(hm);
        return getCardinality(null,edges, max, hm);
    }

    public static int getCardinality(String BB_Path, ArrayList<Edge> edges, int playerCount, HashMap<Integer,Integer> hm) {
        int next = playerCount;
        try {
            FileWriter myWriter = new FileWriter("tmp_out.txt");
            myWriter.write((playerCount * 2) + " " + (edges.size() * 2 + (next) * (next)) + "\n");
            System.out.print((playerCount * 2) + " " + (edges.size() * 2 + (next) * (next)) + "\n");
            for (Edge e : edges) {
                myWriter.write(hm.get(e.getFrom()) + " " + hm.get(e.getTo()) + " 1\n");
                myWriter.write((hm.get(e.getFrom()) + next) + " " + (hm.get(e.getTo()) + next) + " " + (edges.size() + 1) + "\n");

                System.out.print(hm.get(e.getFrom()) + " " + hm.get(e.getTo()) + " 1\n");
                System.out.print((hm.get(e.getFrom()) + next) + " " + (hm.get(e.getTo()) + next) + " " + (edges.size() + 1) + "\n");

            }
            for (int i = 0; i < playerCount; i++) {
                for (int j = playerCount; j < playerCount * 2; j++) {
                    myWriter.write(i + " " + j + " " + (edges.size() + 1) + "\n");

                    System.out.print(i + " " + j + " " + (edges.size() + 1) + "\n");
                }
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //spusti BB subor na tmp_out
        //find cost in BB output
        //cardinality = cost % players (pocet sparovanych dvojic)
        return 0;
    }


    public static void main(String[] args) {
        ArrayList<Edge> test = new ArrayList<>();
        HashMap<Integer,Integer> hm = new HashMap<>();
        ArrayList<Boolean> b = new ArrayList<>();
        /*hm.put(0,0);
        hm.put(1,1);
        hm.put(2,2);
        hm.put(3,3);
        hm.put(4,4);
        hm.put(5,5);
        hm.put(6,6);
        hm.put(7,7);
        hm.put(8,8);
        hm.put(9,9);
        hm.put(10,10);
        hm.put(11,11);*/

        /*for (int i=0; i<12; i++) b.add(false);

        //for(int i=4; i<8; i++) b.set(i, true);
        test.add(new Edge(0,1));
        test.add(new Edge(0,2));
        test.add(new Edge(0,3));

        test.add(new Edge(4,5));
        test.add(new Edge(4,6));
        test.add(new Edge(4,7));

        test.add(new Edge(8,9));
        test.add(new Edge(8,10));
        test.add(new Edge(8,11));

        bbCardinality(null, test, b, 12);*/

        for (int i=0; i<24; i++) b.add(false);

        for(int i=8; i<16; i++) b.set(i, true);
        test.add(new Edge(0,1));
        test.add(new Edge(0,2));
        test.add(new Edge(0,3));

        test.add(new Edge(4,5));
        test.add(new Edge(4,6));
        test.add(new Edge(4,7));

        /*test.add(new Edge(8,9));
        test.add(new Edge(8,10));
        test.add(new Edge(8,11));

        test.add(new Edge(12,13));
        test.add(new Edge(12,14));
        test.add(new Edge(12,15));
*/
        test.add(new Edge(16,17));
        test.add(new Edge(16,18));
        test.add(new Edge(16,19));

        test.add(new Edge(20,21));
        test.add(new Edge(20,22));
        test.add(new Edge(20,23));


        bbCardinality(null, test, b, 24);
        long i = 0;
        /*long k = Long.valueOf(400000);
        while(i<k){
            i++;
            System.out.println(i);
        }*/
    }
}