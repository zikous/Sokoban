package entity;


public abstract class Mobile {
    private Zone zone;

    public abstract boolean deplacer(Direction direction);

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        if (this.zone != null) {
            this.zone.setMobile(null); // Détacher du précédent zone
        }
        this.zone = zone;
        if (zone != null) {
            zone.setMobile(this); // Attacher au nouveau zone
        }
    }

}
