package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("9eb05132-220b-4b3b-9660-77f0aef98691")
public class Caisse extends Mobile {
    @objid ("05c2e0de-8f78-4af9-b5fe-bb3de566fa22")
    @Override
    public boolean deplacer(Direction direction) {
        if (getZone() == null) {
            return false;
        }
        
        Position positionCible = getZone().getPosition().getPositionAdjacente(direction);
        if (positionCible != null && positionCible.getZone().estLibre()) {
            setZone(positionCible.getZone());
            return true;
        }
        return false;
    }

}
