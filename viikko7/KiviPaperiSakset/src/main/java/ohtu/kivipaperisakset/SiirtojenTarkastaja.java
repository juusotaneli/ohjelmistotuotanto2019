package ohtu.kivipaperisakset;

public abstract class SiirtojenTarkastaja {

    public static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

}