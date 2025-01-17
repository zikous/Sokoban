package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("9eb05132-220b-4b3b-9660-77f0aef98691")
public class Caisse extends Mobile {
    @objid ("8b22ed40-c45d-4b63-8f68-aa3a2ebdc5ed")
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
