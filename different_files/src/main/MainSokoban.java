package main;

import javax.swing.SwingUtilities;
import boundary.MaFenetre;
import control.Controleur;

public class MainSokoban {
    public static void main(String[] args) {
        Controleur controleur = new Controleur();
        SwingUtilities.invokeLater(() -> new MaFenetre(controleur));
    }

}
