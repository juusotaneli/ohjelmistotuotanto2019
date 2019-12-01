/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

/**
 *
 * @author Juuso
 */
public class Solmu implements Comparable <Solmu>{
    public int alku, etaisyys;
    
    public Solmu (int etaisyys, int alku) {
        this.alku = alku;
        this.etaisyys = etaisyys;
       
    }
    @Override
    public int compareTo(Solmu s) {
        if (this.etaisyys < s.etaisyys) {
            return -1;
        }else if (this.etaisyys > s.etaisyys) {
            return 1;
        }
        return 0;
        
    }

}
