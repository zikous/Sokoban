package control;


public class NiveauManager {
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

    private int niveauCourant = 0;

    public int[][] getNiveauCourant() {
        return NIVEAUX[niveauCourant];
    }

    public boolean passerAuNiveauSuivant() {
        if (niveauCourant < NIVEAUX.length - 1) {
            niveauCourant++;
            return true;
        }
        return false;
    }

    public boolean estDernierNiveau() {
        return niveauCourant == NIVEAUX.length - 1;
    }

}
