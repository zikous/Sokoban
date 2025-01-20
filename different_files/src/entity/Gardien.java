package entity;


public class Gardien extends Mobile {
    private Direction currentDirection = Direction.BAS;

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

    public Direction getCurrentDirection() {
        return currentDirection;
    }

}
