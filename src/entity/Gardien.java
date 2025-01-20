package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("8ae70f11-a494-4244-886a-d50771bc499c")
public class Gardien extends Mobile {
    @objid ("e9983594-8e0e-4692-9917-6bfbc3579265")
    private Direction currentDirection = Direction.BAS;

    @objid ("f401de07-e62a-4410-8901-5ffe6b7d7d61")
    @Override
    public boolean deplacer(Direction direction) {
        this.currentDirection = direction;
        
        if (getZone() == null) {
            return false;
        }
        
        Position positionCible = getZone().getPosition().getPositionAdjacente(direction);
        if (positionCible == null) {
            return false;
        }
        
        Zone zoneCible = positionCible.getZone();
        
        if (zoneCible.estLibre()) {
            setZone(zoneCible);
            return true;
        }
        
        if (zoneCible.getMobile() instanceof Caisse) {
            Position positionApresCaisse = positionCible.getPositionAdjacente(direction);
            if (positionApresCaisse != null && positionApresCaisse.getZone().estLibre()) {
                Caisse caisse = (Caisse) zoneCible.getMobile();
                caisse.setZone(positionApresCaisse.getZone());
                setZone(zoneCible);
                return true;
            }
        }
        return false;
    }

    @objid ("309a5466-4196-4512-b17e-633277cea512")
    public Direction getCurrentDirection() {
        return currentDirection;
    }

}
