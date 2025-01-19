package entity;


public class Position {
// Représente la ligne de la position dans l'entrepôt
    private int ligne;

// Représente la colonne de la position dans l'entrepôt
    private int colonne;

// Zone associée à cette position (par exemple, une zone contenant une caisse ou un mur)
    private Zone zone;

// Référence à l'entrepôt auquel cette position appartient
    private Entrepot entrepot;

// Constructeur qui initialise une position donnée avec ses coordonnées et l'entrepôt
    public Position(int ligne, int colonne, Entrepot entrepot) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.entrepot = entrepot;
    }

// Accesseur pour obtenir la ligne de la position
    public int getLigne() {
        return ligne;
    }

// Accesseur pour obtenir la colonne de la position
    public int getColonne() {
        return colonne;
    }

// Accesseur pour obtenir la zone associée à cette position
    public Zone getZone() {
        return zone;
    }

// Modificateur pour définir la zone de cette position
    public void setZone(Zone zone) {
        this.zone = zone;
    }

// Méthode qui permet d'obtenir la position adjacente dans la direction spécifiée (Haut, Bas, Gauche, Droite)
    public Position getPositionAdjacente(Direction direction) {
        int nouvelleLigne = ligne;
        int nouvelleColonne = colonne;
        
        // En fonction de la direction donnée, la ligne ou la colonne est mise à jour
        switch (direction) {
            case HAUT:
                nouvelleLigne--;
                break;
            case BAS:
                nouvelleLigne++;
                break;
            case GAUCHE:
                nouvelleColonne--;
                break;
            case DROITE:
                nouvelleColonne++;
                break;
        }
        
        // Retourne la position adjacente dans l'entrepôt
        return entrepot.getPosition(nouvelleLigne, nouvelleColonne);
    }

}
