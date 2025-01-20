package entity;


public class Zone {
    private boolean estCible;

    private boolean estMur;

    private Mobile mobile;

    private Position position;

    public Zone(Position position) {
        this.position = position;
        position.setZone(this);
    }

    public boolean estLibre() {
        return !estMur && mobile == null;
    }

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

    public boolean contientCaisse() {
        return mobile instanceof Caisse;
    }

    public boolean estComplete() {
        return estCible && contientCaisse();
    }

}
