package control;

import entity.*;
import entity.Direction;
import entity.Entrepot;
import entity.Gardien;

public class Controleur {
    private int[][] niveauInitial; // Stocke le niveau initial pour la réinitialisation

    private Gardien gardien; // Le gardien du jeu

    private Entrepot entrepot; // L'entrepôt représentant le plateau de jeu

    private NiveauManager niveauManager; // Gère les niveaux du jeu

    public void action(Direction direction) {
        if (!entrepot.estTermine() && gardien.getZone() != null) {
            gardien.deplacer(direction); // Déplace le gardien dans la direction donnée
        }
    }

    public Controleur() {
        this.entrepot = new Entrepot();
        this.gardien = new Gardien();
        this.niveauManager = new NiveauManager();
        chargerNiveauCourant(); // Charge le niveau actuel
    }

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

    public boolean estTermine() {
        return entrepot.estTermine(); // Vérifie si le niveau est terminé
    }

    public void reinitialiserNiveau() {
        if (niveauInitial != null) {
            chargerNiveau(niveauInitial); // Réinitialise le niveau
        }
    }

    public Entrepot getEntrepot() {
        return entrepot; // Retourne l'entrepôt
    }

    public Gardien getGardien() {
        return gardien; // Retourne le gardien
    }

    private void chargerNiveauCourant() {
        chargerNiveau(niveauManager.getNiveauCourant()); // Charge le niveau actuel
    }

    public boolean estDernierNiveau() {
        return niveauManager.estDernierNiveau(); // Vérifie si c'est le dernier niveau
    }

    public void passerAuNiveauSuivant() {
        if (niveauManager.passerAuNiveauSuivant()) {
            chargerNiveauCourant(); // Passe au niveau suivant
        }
    }

}
