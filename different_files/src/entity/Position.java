package entity;


public class Position {
    private int ligne;

    private int colonne;

    private Zone zone;

    private Entrepot entrepot;

    public Position(int ligne, int colonne, Entrepot entrepot) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.entrepot = entrepot;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Position getPositionAdjacente(Direction direction) {
        int nouvelleLigne = ligne;
        int nouvelleColonne = colonne;
        
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
        return entrepot.getPosition(nouvelleLigne, nouvelleColonne);
    }

}
