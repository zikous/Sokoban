package main;

import boundary.MaFenetre;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import control.Controleur;

@objid ("8322ca96-73aa-45bb-8888-a26cc833a59b")
public class Main {
    @objid ("e7b7a9a5-8c1d-498c-abfc-38bec5754084")
    public static void main(String[] args) {
        Controleur controleur = new Controleur();
        MaFenetre fenetre = new MaFenetre(controleur);
        fenetre.setVisible(true);
    }

}
