package entity;


public class Gardien extends Mobile {
    private Direction currentDirection = Direction.BAS;

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

    public Direction getCurrentDirection() {
        return currentDirection;
    }

}
