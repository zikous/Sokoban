package control;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("c434b8c5-f6cd-467e-9fdd-b099fbb0ae65")
public class NiveauManager {
    @objid ("558f7496-3377-4a8e-ab51-48386b6fa107")
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
            {1, 1, 1, 1, 1, 1, 1},
            {1, 4, 0, 0, 3, 2, 1},
            {1, 1, 1, 1, 1, 1, 1}
        },
        // Niveau 3
        {
            {1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 0, 3, 0, 3, 0, 1},
            {1, 0, 0, 4, 0, 0, 1},
            {1, 0, 2, 0, 2, 0, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1}
        },
        // Niveau 4
        {
            {1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 2, 1, 0, 1},
            {1, 0, 1, 3, 1, 0, 1},
            {1, 0, 0, 4, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1}
        },
        // Niveau 5
        {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 3, 0, 1, 0, 3, 0, 2, 1},
            {1, 0, 0, 2, 0, 3, 0, 0, 2, 1},
            {1, 1, 1, 0, 1, 1, 0, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 2, 1},
            {1, 0, 0, 0, 1, 0, 3, 0, 0, 1},
            {1, 0, 0, 4, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        },
        // Niveau 6
        {
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 2, 2, 1},
            {1, 0, 3, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 3, 1, 0, 2, 2, 1},
            {1, 1, 3, 4, 0, 0, 0, 0, 1},
            {1, 0, 0, 3, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1}
        },
        // Niveau 7
        {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 3, 0, 1, 0, 3, 0, 0, 1},
            {1, 0, 0, 0, 0, 3, 0, 0, 2, 1},
            {1, 0, 3, 0, 1, 0, 0, 0, 0, 1},
            {1, 1, 1, 0, 1, 1, 0, 1, 1, 1},
            {1, 2, 0, 0, 0, 0, 0, 0, 2, 1},
            {1, 0, 0, 0, 1, 1, 0, 0, 0, 1},
            {1, 0, 4, 0, 0, 0, 0, 2, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        },
        // Niveau 8
        {
            {1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 1},
            {1, 0, 3, 0, 4, 1},
            {1, 1, 5, 0, 0, 1},
            {1, 0, 5, 0, 1, 1},
            {1, 0, 5, 0, 1, 1},
            {1, 0, 5, 0, 1, 1},
            {1, 0, 2, 0, 1, 1},
            {1, 1, 1, 1, 1, 1}
        }
    };

    @objid ("3418c9de-8e94-439a-8db4-923d5d4946b2")
    private int niveauCourant = 0; // Niveau actuel

    @objid ("5f507c65-bb08-48db-97d3-283fdf031c42")
    public int[][] getNiveauCourant() {
        return NIVEAUX[niveauCourant]; // Retourne le niveau actuel
    }

    @objid ("c9f7c36e-c579-4660-886f-e79c3173045c")
    public boolean passerAuNiveauSuivant() {
        if (niveauCourant < NIVEAUX.length - 1) {
            niveauCourant++; // Passe au niveau suivant
            return true;
        }
        return false; // Dernier niveau atteint
    }

    @objid ("15d9655b-6e6d-4383-ae44-d6f30d0b7775")
    public boolean estDernierNiveau() {
        return niveauCourant == NIVEAUX.length - 1; // Vérifie si c'est le dernier niveau
    }

}
