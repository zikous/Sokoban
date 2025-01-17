package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("9eb05132-220b-4b3b-9660-77f0aef98691")
public class Caisse extends Mobile {

    // Définition de la méthode de déplacement pour la caisse
    @objid ("8b22ed40-c45d-4b63-8f68-aa3a2ebdc5ed")
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
        return false;  // Le déplacement a échoué si la zone n'est pas libre ou la position est invalide
    }
}
