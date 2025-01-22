package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d70276ca-e16c-4afb-817b-5773bb1819b1")
public class Position {
    @objid ("f3bb811f-cfaa-43c5-b082-91920119117d")
    private int ligne;

    @objid ("0b673a01-2902-4342-ab15-037a7ac1229d")
    private int colonne;

    @objid ("d32c7d33-dd39-44b4-8be8-088dbcc44ea1")
    private Zone zone;

    @objid ("9fc820ca-0578-47d2-ac23-bd28896b7809")
    private Entrepot entrepot;

    @objid ("ad6ed497-d0e7-4fa3-b872-05a1eb1beb13")
    public Position(int ligne, int colonne, Entrepot entrepot) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.entrepot = entrepot;
    }

    @objid ("58034ea6-90ce-492b-aad8-7d2c903e8451")
    public int getLigne() {
        return ligne;
    }

    @objid ("70dfd794-950f-4f6a-b1b7-cea865ea08d0")
    public int getColonne() {
        return colonne;
    }

    @objid ("591e493a-a22b-4528-a864-7f936c039651")
    public Zone getZone() {
        return zone;
    }

    @objid ("dfe1ecb8-75c1-4cde-a842-c283f23ed75d")
    public void setZone(Zone zone) {
        this.zone = zone;
    }

    @objid ("840608b3-080f-42ca-ad7b-5aa75ebbd69e")
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
