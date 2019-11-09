
package ohtu.verkkokauppa;

import java.util.List;

public interface KirjanpitoInterface {
    public void lisaaTapahtuma(String tapahtuma);
    public List<String> getTapahtumat();
}