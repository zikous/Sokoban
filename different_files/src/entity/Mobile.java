package entity;


public abstract class Mobile {
// Représente la zone dans laquelle l'objet mobile (Gardien, Caisse) se trouve.
    private Zone zone;

// Méthode abstraite qui permet à chaque sous-classe (par exemple, Gardien ou Caisse) de définir son propre comportement
// de déplacement. Cette méthode sera implémentée dans les sous-classes.
    public abstract boolean deplacer(Direction direction);

// Permet de récupérer la zone actuelle de l'objet mobile.
    public Zone getZone() {
        return zone;
    }

// Permet de définir la zone de l'objet mobile. Cette méthode met à jour la zone et veille à maintenir
// la cohérence de la relation entre l'objet mobile et la zone (en la retirant de l'ancienne zone si nécessaire).
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
