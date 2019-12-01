package bellman.ford;

import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        int n = 15000000;
        BellmanFord l = new BellmanFord(n);
        Random r = new Random(1337);
        for (int i = 0; i < n; i++) {
            l.lisaaKaari(r.nextInt(100000) + 1, r.nextInt(100000) + 1, r.nextInt(100000) + 1);
        }
        long alku = System.nanoTime();
        l.luo(1, 100);
        long loppu = System.nanoTime();
        System.out.println("Aikaa kului "+((loppu-alku)/1e9)+" s");

    }
}
    

