package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {
    String n;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }
    @Override
    public void suorita() {
        n = tuloskentta.getText();
        tuloskentta.setText("0");
    }
    @Override
    public void undo() {
        tuloskentta.setText(n);
    }
}