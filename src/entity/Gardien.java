package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("8ae70f11-a494-4244-886a-d50771bc499c")
public class Gardien extends Mobile {
    
    // Implémente la méthode de déplacement du gardien
    @objid ("f401de07-e62a-4410-8901-5ffe6b7d7d61")
    @Override
    public boolean deplacer(Direction direction) {
        // Vérifie si la zone actuelle du gardien est nulle (non définie)
        if (getZone() == null) {
            return false;  // Si la zone est nulle, le gardien ne peut pas se déplacer
        }
        
        // Récupère la position cible adjacente dans la direction souhaitée
        Position positionCible = getZone().getPosition().getPositionAdjacente(direction);
        
        // Vérifie si la position cible est valide
        if (positionCible == null) {
            return false;  // Si la position cible est invalide, le déplacement échoue
        }
        
        // Récupère la zone associée à la position cible
        Zone zoneCible = positionCible.getZone();
        
        // Si la zone cible est libre, déplace le gardien
        if (zoneCible.estLibre()) {
            setZone(zoneCible);  // Déplace le gardien dans la nouvelle zone
            return true;  // Déplacement réussi
        }
        
        // Si la zone cible contient une caisse, essaie de déplacer la caisse
        if (zoneCible.getMobile() instanceof Caisse) {
            // Récupère la position après la caisse dans la même direction
            Position positionApresCaisse = positionCible.getPositionAdjacente(direction);
            
            // Vérifie si la position après la caisse est valide et libre
            if (positionApresCaisse != null && positionApresCaisse.getZone().estLibre()) {
                Caisse caisse = (Caisse) zoneCible.getMobile();  // Récupère la caisse
                caisse.setZone(positionApresCaisse.getZone());  // Déplace la caisse dans la nouvelle zone
                setZone(zoneCible);  // Déplace le gardien dans la zone cible
                return true;  // Déplacement réussi
            }
        }
        
        // Si aucune des conditions de déplacement n'est remplie, le mouvement échoue
        return false;
    }
}
