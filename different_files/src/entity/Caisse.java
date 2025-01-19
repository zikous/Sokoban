package entity;


public class Caisse extends Mobile {
// Définition de la méthode de déplacement pour la caisse
    @Override
    public boolean deplacer(Direction direction) {
        // Vérifie si la caisse n'est pas dans une zone
        if (getZone() == null) {
            return false;  // Impossible de déplacer si la caisse n'est pas assignée à une zone
        }
        
        // Récupère la position cible en fonction de la direction
        Position positionCible = getZone().getPosition().getPositionAdjacente(direction);
        
        // Vérifie si la position cible est valide et que la zone est libre
        if (positionCible != null && positionCible.getZone().estLibre()) {
            // Déplace la caisse vers la zone cible
            setZone(positionCible.getZone());
            return true;  // Le déplacement a réussi
        }
        return false; // Le déplacement a échoué si la zone n'est pas libre ou la position est invalide
    }

}
