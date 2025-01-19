package entity;

import java.util.ArrayList;
import java.util.List;

public class Entrepot {
// Liste des positions dans l'entrepôt
    private List<Position> positions = new ArrayList<>();

// Liste des zones dans l'entrepôt
    private List<Zone> zones = new ArrayList<>();

// Initialisation de l'entrepôt avec un certain nombre de lignes et de colonnes
    public void initialiser(int lignes, int colonnes) {
        positions.clear();  // Vide la liste des positions
        zones.clear();      // Vide la liste des zones
        
        // Crée une position et une zone pour chaque cellule de la grille
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                Position position = new Position(i, j, this);
                Zone zone = new Zone(position);
                positions.add(position);  // Ajoute la position à la liste
                zones.add(zone);          // Ajoute la zone à la liste
            }
        }
    }

// Récupère une position par ses coordonnées de ligne et de colonne
    public Position getPosition(int ligne, int colonne) {
        // Recherche la position dans la liste
        for (Position position : positions) {
            if (position.getLigne() == ligne && position.getColonne() == colonne) {
                return position;  // Retourne la position correspondante
            }
        }
        return null; // Retourne null si la position n'est pas trouvée
    }

// Vérifie si le niveau est terminé (toutes les cibles sont complètes)
    public boolean estTermine() {
        // Vérifie que toutes les zones de type "CIBLE" sont complètes
        return zones.stream().filter(Zone::isEstCible).allMatch(Zone::estComplete);
    }

// Réinitialise l'état de toutes les zones de l'entrepôt
    public void clear() {
        // Réinitialise chaque zone (enlevant les mobiles, les murs, et les cibles)
        for (Zone zone : zones) {
            zone.setMobile(null);   // Retire tout objet mobile dans la zone
            zone.setEstMur(false);  // Enlève le mur de la zone
            zone.setEstCible(false); // Enlève la cible de la zone
        }
    }

// Place un élément (mur, cible, caisse) dans l'entrepôt à une position donnée
    public void placerElement(int ligne, int colonne, TypeElement type) {
        // Récupère la position de l'élément à placer
        Position position = getPosition(ligne, colonne);
        if (position == null) return;  // Si la position n'existe pas, on ne fait rien
        
        Zone zone = position.getZone();  // Récupère la zone associée à cette position
        switch (type) {
            case MUR:
                zone.setEstMur(true);  // Définit la zone comme un mur
                break;
            case CIBLE:
                zone.setEstCible(true);  // Définit la zone comme une cible
                break;
            case CAISSE:
                // Si la zone est libre, crée une nouvelle caisse et l'assigne à cette zone
                if (zone.estLibre()) {
                    new Caisse().setZone(zone);
                }
                break;
            default:
                // Si le type d'élément n'est pas reconnu, on lance une exception
                throw new IllegalArgumentException("Type d'élément non géré : " + type);
        }
    }

    @Override
    public String toString() {
        int maxLigne = -1;
        int maxColonne = -1;
        for (Position pos : positions) {
            maxLigne = Math.max(maxLigne, pos.getLigne());
            maxColonne = Math.max(maxColonne, pos.getColonne());
        }
        
        String result = "";
        for (int i = 0; i <= maxLigne; i++) {
            for (int j = 0; j <= maxColonne; j++) {
                Position pos = getPosition(i, j);
                Zone zone = pos.getZone();
        
                char symbole = ' ';
                if (zone.isEstMur()) {
                    symbole = '#';
                } else if (zone.getMobile() instanceof Gardien) {
                    symbole = '@';
                } else if (zone.contientCaisse()) {
                    symbole = zone.isEstCible() ? '*' : '$';
                } else if (zone.isEstCible()) {
                    symbole = '.';
                }
                result += symbole + " ";
            }
            result += "\n";
        }
        return result;
    }

}
