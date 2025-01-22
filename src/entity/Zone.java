package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("9af2c4bd-37a4-4f34-842d-16cbf4b86195")
public class Zone {
    @objid ("021efbba-f89c-4109-a70d-d034de44f682")
    private boolean estCible;

    @objid ("a84ef458-cf71-4300-9701-3557313c5da1")
    private boolean estMur;

    @objid ("c1553619-91b0-4736-a679-0fe7a05779f0")
    private Mobile mobile;

    @objid ("8482332f-d8a0-49ca-9b56-ba538cc17a7c")
    private Position position;

    @objid ("38f6ed02-91a7-409d-bf4c-d8081b4fa007")
    public Zone(Position position) {
        this.position = position;
        position.setZone(this);
    }

    @objid ("24ff9970-aaa1-4d1a-be88-79694789e364")
    public boolean estLibre() {
        return !estMur && mobile == null;
    }

    @objid ("7b147fa4-d337-42e4-ab15-ab5d5312dabf")
    public boolean isEstCible() {
        return estCible;
    }

    @objid ("36691930-369b-4cb6-b237-fea6d3c04035")
    public void setEstCible(boolean estCible) {
        this.estCible = estCible;
    }

    @objid ("ed3c1992-05ee-4854-8986-b6e0ffe04446")
    public boolean isEstMur() {
        return estMur;
    }

    @objid ("d4075419-bf5b-4b29-a169-751322f217b3")
    public void setEstMur(boolean estMur) {
        this.estMur = estMur;
    }

    @objid ("43235556-9ec2-44ad-8410-6db1bb6544e0")
    public Mobile getMobile() {
        return mobile;
    }

    @objid ("b3e19607-8fb2-41b6-902d-285c37a93adb")
    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    @objid ("b82730a7-b373-45b4-a115-38dc594eca41")
    public Position getPosition() {
        return position;
    }

    @objid ("686f7b7d-0dc7-43b7-9c7a-847923cb0aa3")
    public boolean contientCaisse() {
        return mobile instanceof Caisse;
    }

    @objid ("dce72272-eb3c-4266-889c-049420664f2b")
    public boolean estComplete() {
        return estCible && contientCaisse();
    }

}
