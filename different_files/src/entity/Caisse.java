package entity;


public class Caisse extends Mobile {
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
