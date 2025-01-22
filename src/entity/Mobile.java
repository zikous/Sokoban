package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("6f78fd2d-486e-4f70-835b-b0aab2dc2231")
public abstract class Mobile {
    @objid ("3f8e34fd-dc65-4f6e-a39d-a3a056776d1a")
    private Zone zone;

    @objid ("41894ddd-772b-44c5-b905-5ef4b5f091a3")
    public abstract boolean deplacer(Direction direction);

    @objid ("7cdf6697-9ce5-4524-9882-5f0daa4ab409")
    public Zone getZone() {
        return zone;
    }

    @objid ("6d2740bc-60bb-423e-923c-a0ef1a2ade23")
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
