package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("8ae70f11-a494-4244-886a-d50771bc499c")
public class Gardien extends Mobile {
    @objid ("5dec00c4-193b-4148-82a0-7cd506abacd3")
    private Direction currentDirection = Direction.BAS;

    @objid ("c03ec4b7-3c71-44be-bbc9-73a4943867d3")
    @Override
    public boolean deplacer(Direction direction) {
        this.currentDirection = direction;
        
        if (getZone() == null) {
            return false; // Pas de zone actuelle
        }
        
        Position positionCible = getZone().getPosition().getPositionAdjacente(direction);
        if (positionCible == null) {
            return false; // Position cible invalide
        }
        
        Zone zoneCible = positionCible.getZone();
        
        if (zoneCible.estLibre()) {
            setZone(zoneCible); // Déplacement simple
            return true;
        }
        
        if (zoneCible.getMobile() instanceof Caisse) {
            Position positionApresCaisse = positionCible.getPositionAdjacente(direction);
            if (positionApresCaisse != null && positionApresCaisse.getZone().estLibre()) {
                Caisse caisse = (Caisse) zoneCible.getMobile();
                caisse.setZone(positionApresCaisse.getZone()); // Pousser la caisse
                setZone(zoneCible); // Déplacer le gardien
                return true;
            }
        }
        return false; // Déplacement impossible
    }

    @objid ("cce45421-6362-4ec8-ac2e-4398fb01abbd")
    public Direction getCurrentDirection() {
        return currentDirection;
    }

}
