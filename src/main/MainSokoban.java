package main;

import javax.swing.SwingUtilities;
import boundary.MaFenetre;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import control.Controleur;

@objid ("f86bce47-71fd-4abc-b0da-0bb470de0376")
public class MainSokoban {
    @objid ("b7713044-c424-4a2b-ada3-7b45ade957ae")
    public static void main(String[] args) {
        Controleur controleur = new Controleur();
        SwingUtilities.invokeLater(() -> new MaFenetre(controleur));
    }

}
