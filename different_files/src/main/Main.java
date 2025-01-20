package main;

import boundary.MaFenetre;
import control.Controleur;

public class Main {
    public static void main(String[] args) {
        Controleur controleur = new Controleur();
        MaFenetre fenetre = new MaFenetre(controleur);
        fenetre.setVisible(true);
    }

}
