
package dijkstra;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int n = 15000000;
        Dijkstra l = new Dijkstra(n);
        Random r = new Random(1337);
        for (int i = 0; i < n; i++) {
            l.lisaaKaari(r.nextInt(100000) + 1, r.nextInt(100000) + 1, r.nextInt(100000) + 1);
            if (i %1000000 == 0 ) {
                System.out.println("joo");
            }
        }
        System.out.println("done");
        long alku = System.nanoTime();
        l.luo(1, 100);
        long loppu = System.nanoTime();
        System.out.println("Aikaa kului "+((loppu-alku)/1e9)+" s");

    }

}
