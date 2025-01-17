package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d70276ca-e16c-4afb-817b-5773bb1819b1")
public class Position {

    // Représente la ligne de la position dans l'entrepôt
    @objid ("a8bd8893-b28b-40d8-9b87-d2302dffe5a8")
    private int ligne;

    // Représente la colonne de la position dans l'entrepôt
    @objid ("623c58ec-74bf-4af7-861c-699fe5a44719")
    private int colonne;

    // Zone associée à cette position (par exemple, une zone contenant une caisse ou un mur)
    @objid ("d32c7d33-dd39-44b4-8be8-088dbcc44ea1")
    private Zone zone;

    // Référence à l'entrepôt auquel cette position appartient
    @objid ("9fc820ca-0578-47d2-ac23-bd28896b7809")
    private Entrepot entrepot;

    // Constructeur qui initialise une position donnée avec ses coordonnées et l'entrepôt
    @objid ("3094f582-2e5d-4ee8-b03c-6827900792b7")
    public Position(int ligne, int colonne, Entrepot entrepot) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.entrepot = entrepot;
    }

    // Accesseur pour obtenir la ligne de la position
    @objid ("ccd05f42-0e49-42b5-b0fa-088bc2e0a29f")
    public int getLigne() {
        return ligne;
    }

    // Accesseur pour obtenir la colonne de la position
    @objid ("75515198-f92b-4647-b2a3-ea88f7ccb4c9")
    public int getColonne() {
        return colonne;
    }

    // Accesseur pour obtenir la zone associée à cette position
    @objid ("7145ac7a-c1e4-442c-8d8f-303e78b16215")
    public Zone getZone() {
        return zone;
    }

    // Modificateur pour définir la zone de cette position
    @objid ("ca9fc007-2578-4a0f-89f8-b6182175b83b")
    public void setZone(Zone zone) {
        this.zone = zone;
    }

    // Méthode qui permet d'obtenir la position adjacente dans la direction spécifiée (Haut, Bas, Gauche, Droite)
    @objid ("b8b01d3a-a2b4-4f93-ab54-f3a2544e13a4")
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
