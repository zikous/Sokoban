package main;

import javax.swing.SwingUtilities;
import boundary.MaFenetre;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import control.Controleur;

@objid ("f86bce47-71fd-4abc-b0da-0bb470de0376")
public class MainSokoban {
    @objid ("b7713044-c424-4a2b-ada3-7b45ade957ae")
    public static void main(String[] args) {
        // Création du niveau (exemple)
        int[][] niveau = {
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                { 1, 0, 3, 0, 1, 0, 3, 0, 0, 1 },
                { 1, 0, 0, 0, 0, 3, 0, 0, 2, 1 },
                { 1, 0, 3, 0, 1, 0, 0, 0, 0, 1 },
                { 1, 1, 1, 0, 1, 1, 0, 1, 1, 1 },
                { 1, 2, 0, 0, 0, 0, 0, 0, 2, 1 },
                { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1 },
                { 1, 0, 6, 3, 0, 0, 0, 2, 0, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
        };
        
        // Initialisation du jeu
        Controleur controleur = new Controleur();
        controleur.chargerNiveau(niveau);
        
        // Lancement de l'interface graphique
        SwingUtilities.invokeLater(() -> new MaFenetre(controleur));
    }

}
