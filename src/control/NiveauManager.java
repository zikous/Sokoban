package control;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("6ba4275c-89a6-4275-b1c3-013068fdeaf3")
public class NiveauManager {
    @objid ("f3b6038f-7e88-4247-8b0c-0cfb3a725456")
    private static final int[][][] NIVEAUX = {
    	    // Niveau 1 
    	    {
    	        {1, 1, 1, 1, 1},
    	        {1, 0, 3, 2, 1},
    	        {1, 0, 4, 0, 1},
    	        {1, 1, 1, 1, 1}
    	    },
    	    // Niveau 2 
            {
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                { 1, 0, 3, 0, 1, 0, 3, 0, 0, 1 },
                { 1, 0, 0, 0, 0, 3, 0, 0, 2, 1 },
                { 1, 0, 3, 0, 1, 0, 0, 0, 0, 1 },
                { 1, 1, 1, 0, 1, 1, 0, 1, 1, 1 },
                { 1, 2, 0, 0, 0, 0, 0, 0, 2, 1 },
                { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1 },
                { 1, 0, 4, 0, 0, 0, 0, 2, 0, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
            },
    	};

    @objid ("4accb8d1-23ea-4ad9-b67e-50c2dbca6ae0")
    private int niveauCourant = 0;

    @objid ("69f6fa46-efe5-4d68-89f3-fed7698a29f1")
    public int[][] getNiveauCourant() {
        return NIVEAUX[niveauCourant];
    }

    @objid ("334d7b0b-5852-4eb6-ab43-e8e24748f5cd")
    public boolean passerAuNiveauSuivant() {
        if (niveauCourant < NIVEAUX.length - 1) {
            niveauCourant++;
            return true;
        }
        return false;
    }

    @objid ("c6363e6c-19a2-4f93-9736-3da0ed95c950")
    public boolean estDernierNiveau() {
        return niveauCourant == NIVEAUX.length - 1;
    }

}
