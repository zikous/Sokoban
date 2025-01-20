package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d70276ca-e16c-4afb-817b-5773bb1819b1")
public class Position {
    @objid ("a8bd8893-b28b-40d8-9b87-d2302dffe5a8")
    private int ligne;

    @objid ("623c58ec-74bf-4af7-861c-699fe5a44719")
    private int colonne;

    @objid ("d32c7d33-dd39-44b4-8be8-088dbcc44ea1")
    private Zone zone;

    @objid ("9fc820ca-0578-47d2-ac23-bd28896b7809")
    private Entrepot entrepot;

    @objid ("3094f582-2e5d-4ee8-b03c-6827900792b7")
    public Position(int ligne, int colonne, Entrepot entrepot) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.entrepot = entrepot;
    }

    @objid ("ccd05f42-0e49-42b5-b0fa-088bc2e0a29f")
    public int getLigne() {
        return ligne;
    }

    @objid ("75515198-f92b-4647-b2a3-ea88f7ccb4c9")
    public int getColonne() {
        return colonne;
    }

    @objid ("7145ac7a-c1e4-442c-8d8f-303e78b16215")
    public Zone getZone() {
        return zone;
    }

    @objid ("ca9fc007-2578-4a0f-89f8-b6182175b83b")
    public void setZone(Zone zone) {
        this.zone = zone;
    }

    @objid ("b8b01d3a-a2b4-4f93-ab54-f3a2544e13a4")
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
