/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra {
    
    ArrayList<Kaari> tiet;
    int etaisyys[];
    boolean kasitelty[];
    int INF;
    ArrayList <Kaari>[]verkko;
    ArrayList <Integer> reitti;

    public Dijkstra(int n) {
        reitti = new ArrayList <> ();
        etaisyys = new int[n + 1];
        kasitelty = new boolean[n + 1];
        INF = 999999999;
        verkko = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {
            etaisyys[i] = INF;
            verkko[i] = new ArrayList<>();
        }
       
    }
    public void lisaaKaari(int a, int b, int p) {
        verkko[a].add(new Kaari(a,b,p));
        verkko[b].add(new Kaari(b,a,p));
        
    }
    public void luo (int x, int y) {
        PriorityQueue <Solmu> pq = new PriorityQueue <> ();
        
        pq.add(new Solmu(0, x));
      
        while (!pq.isEmpty()) {
            int solmu = pq.poll().alku;
            
            if (kasitelty[solmu]) {
                continue;
            }
            kasitelty[solmu] = true;
            etaisyys[x] = 0;
            for (Kaari t : verkko[solmu]) {
                int nyky = etaisyys[t.loppu];
                int uusi = etaisyys[solmu] + t.paino;
                if (nyky > uusi) {
                    etaisyys[t.loppu] = uusi;
                    pq.add(new Solmu(uusi, t.loppu));
                    
                }
     
            }
            
        }
        System.out.println(etaisyys[y]);
        
    }
    
}
