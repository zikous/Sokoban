package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("6f78fd2d-486e-4f70-835b-b0aab2dc2231")
public abstract class Mobile {
// Représente la zone dans laquelle l'objet mobile (Gardien, Caisse) se trouve.
    @objid ("3f8e34fd-dc65-4f6e-a39d-a3a056776d1a")
    private Zone zone;

// Méthode abstraite qui permet à chaque sous-classe (par exemple, Gardien ou Caisse) de définir son propre comportement
// de déplacement. Cette méthode sera implémentée dans les sous-classes.
    @objid ("e5a6eff8-29c5-4e6d-ab8b-0c127e57d5df")
    public abstract boolean deplacer(Direction direction);

// Permet de récupérer la zone actuelle de l'objet mobile.
    @objid ("8237b83e-e9e3-4cb4-a92d-36412ee2416a")
    public Zone getZone() {
        return zone;
    }

// Permet de définir la zone de l'objet mobile. Cette méthode met à jour la zone et veille à maintenir
// la cohérence de la relation entre l'objet mobile et la zone (en la retirant de l'ancienne zone si nécessaire).
    @objid ("6cc402cd-c3ea-4a16-80d7-ad7d38125c7a")
    public void setZone(Zone zone) {
        // Si l'objet mobile était déjà dans une zone, on enlève la référence à cet objet dans cette zone
        if (this.zone != null) {
            this.zone.setMobile(null);  // On enlève l'objet mobile de l'ancienne zone
        }
        
        this.zone = zone;  // On définit la nouvelle zone de l'objet mobile
        
        // Si la nouvelle zone n'est pas nulle, on associe l'objet mobile à cette zone
        if (zone != null) {
            zone.setMobile(this);  // On place l'objet mobile dans la nouvelle zone
        }
    }

}
