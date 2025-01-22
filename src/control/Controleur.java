package control;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import entity.*;
import entity.Direction;
import entity.Entrepot;
import entity.Gardien;

@objid ("14f91e90-d328-4cde-b2ef-ec5fcc074d37")
public class Controleur {
    @objid ("5b5d4831-b00c-4c2b-8ca5-94ea1806af9b")
    private int[][] niveauInitial; // Stocke le niveau initial pour la réinitialisation

    @objid ("eae08b9d-694a-4850-886b-2e97debff8ac")
    private Gardien gardien; // Le gardien du jeu

    @objid ("54da0ef2-d468-468a-9149-9b67e21c4ff3")
    private Entrepot entrepot; // L'entrepôt représentant le plateau de jeu

    @objid ("bdfc2043-f164-416d-be07-b50d22d00c43")
    private NiveauManager niveauManager; // Gère les niveaux du jeu

    @objid ("1a98906f-06e0-44d7-a8f9-d9d318126874")
    public void action(Direction direction) {
        if (!entrepot.estTermine() && gardien.getZone() != null) {
            gardien.deplacer(direction); // Déplace le gardien dans la direction donnée
        }
    }

    @objid ("528995e0-acfc-496c-8a7c-528cdac7f465")
    public Controleur() {
        this.entrepot = new Entrepot();
        this.gardien = new Gardien();
        this.niveauManager = new NiveauManager();
        chargerNiveauCourant(); // Charge le niveau actuel
    }

    @objid ("9a368545-d618-450d-95e4-5cfc0b051d66")
    public void chargerNiveau(int[][] niveau) {
        // Copie le niveau pour la réinitialisation
        this.niveauInitial = new int[niveau.length][niveau[0].length];
        for (int i = 0; i < niveau.length; i++) {
            System.arraycopy(niveau[i], 0, niveauInitial[i], 0, niveau[i].length);
        }
        
        entrepot.clear(); // Réinitialise l'entrepôt
        entrepot.initialiser(niveau.length, niveau[0].length); // Initialise l'entrepôt avec la taille du niveau
        
        // Place les éléments du niveau dans l'entrepôt
        for (int i = 0; i < niveau.length; i++) {
            for (int j = 0; j < niveau[0].length; j++) {
                int value = niveau[i][j];
                if (value != 0) { // 0 représente un espace vide
                    TypeElement type = TypeElement.fromInt(value);
                    switch (type) {
                        case MUR:
                            entrepot.placerElement(i, j, TypeElement.MUR);
                            break;
                        case CIBLE:
                            entrepot.placerElement(i, j, TypeElement.CIBLE);
                            break;
                        case CAISSE:
                            entrepot.placerElement(i, j, TypeElement.CAISSE);
                            break;
                        case GARDIEN:
                            Position pos = entrepot.getPosition(i, j);
                            if (pos != null && pos.getZone().estLibre()) {
                                gardien.setZone(pos.getZone()); // Place le gardien
                            }
                            break;
                        case CIBLE_CAISSE:
                            entrepot.placerElement(i, j, TypeElement.CIBLE);
                            entrepot.placerElement(i, j, TypeElement.CAISSE);
                            break;
                        case GARDIEN_CIBLE:
                            Position posGardien = entrepot.getPosition(i, j);
                            if (posGardien != null && posGardien.getZone().estLibre()) {
                                gardien.setZone(posGardien.getZone()); // Place le gardien sur une cible
                            }
                            entrepot.placerElement(i, j, TypeElement.CIBLE);
                            break;
                    }
                }
            }
        }
    }

    @objid ("04538338-5d8a-4caf-9efb-707aba680e17")
    public boolean estTermine() {
        return entrepot.estTermine(); // Vérifie si le niveau est terminé
    }

    @objid ("86bf3ee7-dbf4-4989-9473-5f86deb70f99")
    public void reinitialiserNiveau() {
        if (niveauInitial != null) {
            chargerNiveau(niveauInitial); // Réinitialise le niveau
        }
    }

    @objid ("fe024fc2-d23f-43e9-9cd7-94c44685090d")
    public Entrepot getEntrepot() {
        return entrepot; // Retourne l'entrepôt
    }

    @objid ("760510ec-c61b-4e14-87be-8b95ef616da0")
    public Gardien getGardien() {
        return gardien; // Retourne le gardien
    }

    @objid ("95fd4892-5220-4adb-a880-1bd0f301f6b2")
    private void chargerNiveauCourant() {
        chargerNiveau(niveauManager.getNiveauCourant()); // Charge le niveau actuel
    }

    @objid ("b8b8d88c-3a81-47cd-8a43-981c635e2e5b")
    public boolean estDernierNiveau() {
        return niveauManager.estDernierNiveau(); // Vérifie si c'est le dernier niveau
    }

    @objid ("1fe891a0-cdf6-4af7-9ecc-2eb0d7d4f98a")
    public void passerAuNiveauSuivant() {
        if (niveauManager.passerAuNiveauSuivant()) {
            chargerNiveauCourant(); // Passe au niveau suivant
        }
    }

}
