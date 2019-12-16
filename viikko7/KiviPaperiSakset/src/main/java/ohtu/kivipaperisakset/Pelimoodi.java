package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Pelimoodi extends SiirtojenTarkastaja {
    private static final Scanner scanner = new Scanner(System.in);

    Aly aly;
    Tuomari tuomari;

    public Pelimoodi(Aly aly, Tuomari tuomari) {
        this.aly = aly;
        this.tuomari = tuomari;
    }

    public void pelaa() {

        if (this.aly != null) {
            System.out.print("Ensimmäisen pelaajan siirto: ");
            String ekanSiirto = scanner.nextLine();
            String tokanSiirto;

            tokanSiirto = aly.annaSiirto();
            System.out.println("Tietokone valitsi: " + tokanSiirto);

            while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
                tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
                System.out.println(tuomari);
                System.out.println();

                System.out.print("Ensimmäisen pelaajan siirto: ");
                ekanSiirto = scanner.nextLine();

                tokanSiirto = aly.annaSiirto();
                System.out.println("Tietokone valitsi: " + tokanSiirto);
                aly.asetaSiirto(ekanSiirto);

            }

            System.out.println();
            System.out.println("Kiitos!");
            System.out.println(tuomari);

        } else {
            System.out.print("Ensimmäisen pelaajan siirto: ");
            String ekanSiirto = scanner.nextLine();
            System.out.print("Toisen pelaajan siirto: ");
            String tokanSiirto = scanner.nextLine();

            while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
                tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
                System.out.println(tuomari);
                System.out.println();

                System.out.print("Ensimmäisen pelaajan siirto: ");
                ekanSiirto = scanner.nextLine();

                System.out.print("Toisen pelaajan siirto: ");
                tokanSiirto = scanner.nextLine();
            }

            System.out.println();
            System.out.println("Kiitos!");
            System.out.println(tuomari);

        }
    }

}
