package main;

import boundary.MaFenetre;
import control.Controleur;

public class MainSokoban {
    public static void main(String[] args) {
        Controleur controleur = new Controleur(); // Crée le contrôleur du jeu
        MaFenetre fenetre = new MaFenetre(controleur); // Crée la fenêtre principale
        fenetre.setVisible(true); // Rend la fenêtre visible
    }

}
