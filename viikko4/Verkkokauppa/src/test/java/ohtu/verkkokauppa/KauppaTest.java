package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);


    }
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    @Test
    public void lisataanKoriinYksiTuote() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "samppanja", 500));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);    
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 500);   
    }
    @Test
    public void lisataanKoriinKaksiEriTuotetta() {

        when(viite.uusi()).thenReturn(11);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.saldo(2)).thenReturn(1); 

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "kalja", 3));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "viinaa", 12));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.lisaaKoriin(2);
        k.tilimaksu("esa", "12345");

        verify(pankki).tilisiirto("esa", 11, "12345", "33333-44455", 15);   
    }
    @Test
    public void lisataanKoriinKaksiSamaaTuotetta() {

        when(viite.uusi()).thenReturn(11);
        when(varasto.saldo(1)).thenReturn(1); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "viinaa", 12));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.lisaaKoriin(1);
        k.tilimaksu("esa", "12345");

        verify(pankki).tilisiirto("esa", 11, "12345", "33333-44455", 24);   
    }
    @Test
    public void lisataanKoriinKaksiSamaaTuotettaJoistaToinenOnLoppuVarastosta() {

        when(viite.uusi()).thenReturn(11);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(1); 
        when(varasto.saldo(2)).thenReturn(0); 

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "viinaa", 12));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "siideri", 3));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.lisaaKoriin(2);
        k.tilimaksu("esa", "12345");

        verify(pankki).tilisiirto("esa", 11, "12345", "33333-44455", 12);   
    }
    @Test
    public void aloitaAsiointiMetodiNollaaEdellisenOstoksenTiedot() {

        when(viite.uusi()).thenReturn(11);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(1); 
        when(varasto.saldo(2)).thenReturn(1); 

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "viinaa", 12));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "siideri", 3));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.lisaaKoriin(2);
        k.tilimaksu("esa", "12345");

        k.aloitaAsiointi();
        k.lisaaKoriin(2);  
        k.tilimaksu("esa", "12345");

        verify(pankki).tilisiirto("esa", 11, "12345", "33333-44455", 3);   
    }
    @Test
    public void jokainenMaksuTapahtumaSaaUudenViitteen () {

        when(viite.uusi()).thenReturn(11).thenReturn(22);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(1); 
        when(varasto.saldo(2)).thenReturn(1); 

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "viinaa", 12));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "siideri", 3));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.lisaaKoriin(2);
        k.tilimaksu("esa", "12345");

        k.aloitaAsiointi();
        k.lisaaKoriin(2);  
        k.tilimaksu("esa", "12345");

        verify(pankki).tilisiirto("esa", 22, "12345", "33333-44455", 3);   
    }
    @Test
    public void poistetaanKoristaYksiJoLisättyTuote () {

        when(viite.uusi()).thenReturn(11);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(1); 
        when(varasto.saldo(2)).thenReturn(1); 

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "viinaa", 12));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "siideri", 3));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.poistaKorista(2);
        
        k.tilimaksu("esa", "12345");
        

        verify(pankki).tilisiirto("esa", 11, "12345", "33333-44455", 12);   
    }


}


