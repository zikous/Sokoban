package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("6f78fd2d-486e-4f70-835b-b0aab2dc2231")
public abstract class Mobile {
    @objid ("3f8e34fd-dc65-4f6e-a39d-a3a056776d1a")
    private Zone zone;

    @objid ("e5a6eff8-29c5-4e6d-ab8b-0c127e57d5df")
    public abstract boolean deplacer(Direction direction);

    @objid ("8237b83e-e9e3-4cb4-a92d-36412ee2416a")
    public Zone getZone() {
        return zone;
    }

    @objid ("6cc402cd-c3ea-4a16-80d7-ad7d38125c7a")
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
