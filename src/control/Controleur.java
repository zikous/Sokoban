package control;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import entity.*;
import entity.Direction;
import entity.Entrepot;
import entity.Gardien;

@objid ("14f91e90-d328-4cde-b2ef-ec5fcc074d37")
public class Controleur {
    @objid ("8e85fa44-a2b6-49fe-ac47-6d4564d03f31")
    private int[][] niveauInitial; // Stocke le niveau initial pour la réinitialisation

    @objid ("eae08b9d-694a-4850-886b-2e97debff8ac")
    private Gardien gardien; // Le gardien du jeu

    @objid ("54da0ef2-d468-468a-9149-9b67e21c4ff3")
    private Entrepot entrepot; // L'entrepôt représentant le plateau de jeu

    @objid ("bdfc2043-f164-416d-be07-b50d22d00c43")
    private NiveauManager niveauManager; // Gère les niveaux du jeu

    @objid ("b4eb871f-b7e0-471c-a89a-31c91670ec60")
    public void action(Direction direction) {
        if (!entrepot.estTermine() && gardien.getZone() != null) {
            gardien.deplacer(direction); // Déplace le gardien dans la direction donnée
        }
    }

    @objid ("34fea563-bd44-44cd-a263-50bf97abbb46")
    public Controleur() {
        this.entrepot = new Entrepot();
        this.gardien = new Gardien();
        this.niveauManager = new NiveauManager();
        chargerNiveauCourant(); // Charge le niveau actuel
    }

    @objid ("440fe7ef-4b0e-4f82-9add-be2866973951")
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

    @objid ("74fc1f4b-efe9-40a8-86c8-fc7b99940210")
    public boolean estTermine() {
        return entrepot.estTermine(); // Vérifie si le niveau est terminé
    }

    @objid ("b87f4879-3f4d-4ee3-9689-fb9edf4701fa")
    public void reinitialiserNiveau() {
        if (niveauInitial != null) {
            chargerNiveau(niveauInitial); // Réinitialise le niveau
        }
    }

    @objid ("45631f7d-3f27-4f99-9d77-e53a04a3392b")
    public Entrepot getEntrepot() {
        return entrepot; // Retourne l'entrepôt
    }

    @objid ("ef9a3c18-f8bc-4723-bd77-7fae01caf358")
    public Gardien getGardien() {
        return gardien; // Retourne le gardien
    }

    @objid ("32529473-bfad-45de-b9c7-9e8b44bc8335")
    private void chargerNiveauCourant() {
        chargerNiveau(niveauManager.getNiveauCourant()); // Charge le niveau actuel
    }

    @objid ("13a92df4-cb65-4ad3-b66f-8693f523ce61")
    public boolean estDernierNiveau() {
        return niveauManager.estDernierNiveau(); // Vérifie si c'est le dernier niveau
    }

    @objid ("db2e28bd-6f3f-41d4-880e-85c39f48d325")
    public void passerAuNiveauSuivant() {
        if (niveauManager.passerAuNiveauSuivant()) {
            chargerNiveauCourant(); // Passe au niveau suivant
        }
    }

}
