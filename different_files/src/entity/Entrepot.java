package entity;

import java.util.ArrayList;
import java.util.List;

public class Entrepot {
    private List<Position> positions = new ArrayList<>();

    private List<Zone> zones = new ArrayList<>();

    public void initialiser(int lignes, int colonnes) {
        positions.clear();
        zones.clear();
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                Position position = new Position(i, j, this);
                Zone zone = new Zone(position);
                positions.add(position);
                zones.add(zone);
            }
        }
    }

    public Position getPosition(int ligne, int colonne) {
        for (Position position : positions) {
            if (position.getLigne() == ligne && position.getColonne() == colonne) {
                return position;
            }
        }
        return null;
    }

    public boolean estTermine() {
        return zones.stream().filter(Zone::isEstCible).allMatch(Zone::estComplete);
    }

    public void clear() {
        for (Zone zone : zones) {
            zone.setMobile(null);
            zone.setEstMur(false);
            zone.setEstCible(false);
        }
    }

    public void placerElement(int ligne, int colonne, TypeElement type) {
        Position position = getPosition(ligne, colonne);
        if (position == null) return;
        
        Zone zone = position.getZone();
        switch (type) {
            case MUR:
                zone.setEstMur(true);
                break;
            case CIBLE:
                zone.setEstCible(true);
                break;
            case CAISSE:
                if (zone.estLibre()) {
                    new Caisse().setZone(zone);
                }
                break;
            default:
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
