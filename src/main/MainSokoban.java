package main;

import boundary.MaFenetre;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import control.Controleur;

@objid ("f86bce47-71fd-4abc-b0da-0bb470de0376")
public class MainSokoban {
    @objid ("64c2c73a-a72e-46c2-9f40-dc89854b09fb")
    public static void main(String[] args) {
        Controleur controleur = new Controleur(); // Crée le contrôleur du jeu
        MaFenetre fenetre = new MaFenetre(controleur); // Crée la fenêtre principale
        fenetre.setVisible(true); // Rend la fenêtre visible
    }

}
