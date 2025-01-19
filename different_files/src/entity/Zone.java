package entity;


public class Zone {
    private boolean estCible; // Indique si la zone est une cible

    private boolean estMur; // Indique si la zone est un mur

    private Mobile mobile; // L'élément mobile présent dans la zone (caisse, gardien, etc.)

    private Position position; // La position de la zone dans l'entrepôt

    public Zone(Position position) {
        this.position = position;
        position.setZone(this);
    }

// Vérifie si la zone est libre (pas de mur, pas d'élément mobile)
    public boolean estLibre() {
        return !estMur && mobile == null;
    }

// Getters et setters
    public boolean isEstCible() {
        return estCible;
    }

    public void setEstCible(boolean estCible) {
        this.estCible = estCible;
    }

    public boolean isEstMur() {
        return estMur;
    }

    public void setEstMur(boolean estMur) {
        this.estMur = estMur;
    }

    public Mobile getMobile() {
        return mobile;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    public Position getPosition() {
        return position;
    }

// Vérifie si la zone contient une caisse
    public boolean contientCaisse() {
        return mobile instanceof Caisse;
    }

// Vérifie si la zone est complète (cible et contient une caisse)
    public boolean estComplete() {
        return estCible && contientCaisse();
    }

}
