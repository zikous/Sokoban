package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("9af2c4bd-37a4-4f34-842d-16cbf4b86195")
public class Zone {
    @objid ("1eedd413-1371-4096-bb6e-50aa43d17c45")
    private boolean estCible;

    @objid ("2d6d7c3a-6de1-43d9-81de-fc28d0a9f7a3")
    private boolean estMur;

    @objid ("c1553619-91b0-4736-a679-0fe7a05779f0")
    private Mobile mobile;

    @objid ("8482332f-d8a0-49ca-9b56-ba538cc17a7c")
    private Position position;

    @objid ("6b55e4b9-e2d9-4ebd-8e98-28205ef2d219")
    public Zone(Position position) {
        this.position = position;
        position.setZone(this);
    }

    @objid ("9652648f-1b97-42b8-afba-7f722fef967a")
    public boolean estLibre() {
        return !estMur && mobile == null;
    }

    @objid ("b6feeb6b-24b3-4d86-83e1-419a1a89330d")
    public boolean isEstCible() {
        return estCible;
    }

    @objid ("484181e2-ca21-47e5-9708-dfc2eda2ce24")
    public void setEstCible(boolean estCible) {
        this.estCible = estCible;
    }

    @objid ("8f1745b8-4dc2-4936-aa7a-ded6df14a7ab")
    public boolean isEstMur() {
        return estMur;
    }

    @objid ("61dd0d3c-2535-49f3-8dfb-05f1d127d1a7")
    public void setEstMur(boolean estMur) {
        this.estMur = estMur;
    }

    @objid ("79062302-75c0-4a0a-91d8-f06414d72b1f")
    public Mobile getMobile() {
        return mobile;
    }

    @objid ("cb555592-9bf4-4ef5-83a5-259f98c91d9e")
    protected void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    @objid ("acfde1a3-8ca4-4432-b5cb-6e1ffadf9003")
    public Position getPosition() {
        return position;
    }

    @objid ("2fb09d7d-aa46-47b2-b8f1-db8041196e6f")
    public boolean contientCaisse() {
        return mobile instanceof Caisse;
    }

    @objid ("4a501c0b-f57d-468b-a386-fa5ad067f129")
    public boolean estComplete() {
        return estCible && contientCaisse();
    }

}
