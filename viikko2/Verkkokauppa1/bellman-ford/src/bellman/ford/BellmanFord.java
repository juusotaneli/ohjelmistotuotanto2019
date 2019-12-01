package bellman.ford;

import java.util.ArrayList;
import java.util.Random;

public class BellmanFord {
    ArrayList<Kaari> kaaret;
    int etaisyys[];
    int INF;

    public BellmanFord(int n) {
        kaaret = new ArrayList<>();
        etaisyys = new int[n + 1];
        INF = 999999999;

        for (int i = 1; i <= n; i++) {
            etaisyys[i] = INF;

        }
        
        
    }

    public void lisaaKaari(int a, int b, int p) {
        kaaret.add(new Kaari(a, b, p));
        kaaret.add(new Kaari(b, a, p));

    }

    public void luo(int x, int y) {
     
        etaisyys[x] = 0;
        while (true) {
            boolean muutos = false;
            for (Kaari k : kaaret) {
                int nyky = etaisyys[k.loppu];
                int uusi = etaisyys[k.alku] + k.paino;
                if (uusi < nyky) {
                    etaisyys[k.loppu] = uusi;
                    muutos = true;
                }
            }
            if (!muutos) {
                break;
            }
        }
        System.out.println(etaisyys[y]);

    }
 
}

