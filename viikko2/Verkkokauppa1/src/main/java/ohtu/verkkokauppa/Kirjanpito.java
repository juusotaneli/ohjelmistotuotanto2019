
package ohtu.verkkokauppa;

import java.util.*;

import org.springframework.stereotype.Component;

@Component
public class Kirjanpito implements KirjanpitoInterface {
    
    private List <String> tapahtumat;
    
    public Kirjanpito() {
        this.tapahtumat = new ArrayList<String>();
    }
    
    public void lisaaTapahtuma(String tapahtuma) {
        this.tapahtumat.add(tapahtuma);
    }

    public List<String> getTapahtumat() {
        return this.tapahtumat;
    }       
}
